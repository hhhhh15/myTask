package com.example.cutivatingapp1_java.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutivatingapp1_java.R;
import com.example.cutivatingapp1_java.databinding.TaskfragmentItemBinding;
import com.example.cutivatingapp1_java.databinding.TaskfragmentItemDialogBinding;
import com.example.cutivatingapp1_java.fragment.TaskTimeFragment;
import com.example.cutivatingapp1_java.Model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    //1.接收数据
    private List<Task> taskList;
    private Context context;
    private TaskfragmentItemDialogBinding dialogBinding;
    public TaskAdapter(Context context,List<Task> taskList) {
        this.taskList = taskList;
        this.context = context;
    }

    //2.创建viewHolder类
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TaskfragmentItemBinding binding;
        public TaskViewHolder(TaskfragmentItemBinding binding) {
            super(binding.getRoot()); // 这里用到了 getRoot()
            this.binding = binding;
        }
    }


    @NonNull
    //创建卡片
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//这个ViewGroup说是recycleview本身
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskfragmentItemBinding binding = TaskfragmentItemBinding.inflate(inflater, parent, false);
        return new TaskViewHolder(binding);

    }

    //这个是给卡片装数据,操作具体任务的数据
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        //获取当前的任务位置
        Task task = taskList.get(position);
        holder.binding.signtime.setText("计划用时"+task.getSetTime());//往卡片里面填充这个计时的时间
        //打开弹窗
        holder.binding.editcard.setOnClickListener(v -> {
            Log.d("任务卡打开编译弹窗", "TaskAdapter的onBindViewHolder: ");
            setDialog();
        });
        //跳转到计时页面，但是携带数据跳转【还剩下能用的计划用时】
        holder.binding.btnTime.setOnClickListener(v -> {
            setDialog();
        });

        //计时页面跳转
        holder.binding.btnTime.setOnClickListener(v -> {
            FragmentActivity activity = (FragmentActivity) v.getContext();

            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer, new TaskTimeFragment()) // 你的计时Fragment
                    .addToBackStack(null) // 加到返回栈，方便按返回键回来
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    //弹窗逻辑
    private void setDialog(){
         dialogBinding = TaskfragmentItemDialogBinding.inflate( LayoutInflater.from(context));
        Dialog dialog=new Dialog(context);

        dialog.setContentView(dialogBinding.getRoot());

        // 🔒 禁止点击空白和返回键关闭
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        //提前将弹窗里面的数据绑定好，再弹窗.show()
        dialogBinding.btnModifyTask.setOnClickListener(v -> {
            hideAllLayouts();
            dialogBinding.modifyTaskLayout.setVisibility(View.VISIBLE);
        });
        dialogBinding.btnSetTime.setOnClickListener(v -> {
            hideAllLayouts();
            dialogBinding.setTimeLayout.setVisibility(View.VISIBLE);
        });

        // ✅ 只有点击“确认”和“X”才能关闭弹窗
        dialogBinding.btnsave.setOnClickListener(v -> {
            // 做一些保存逻辑...
            dialog.dismiss();
        });

        dialogBinding.btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();

    }
    //也是弹窗里面隐藏的方法
    private void hideAllLayouts() {
        dialogBinding.modifyTaskLayout.setVisibility(View.GONE);
        dialogBinding.setTimeLayout.setVisibility(View.GONE);
        // 如果你以后还有其他区域，比如 notesLayout，也可以一并加进来
    }
}
