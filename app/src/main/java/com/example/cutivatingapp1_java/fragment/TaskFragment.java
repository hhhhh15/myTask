package com.example.cutivatingapp1_java.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.cutivatingapp1_java.R;
import com.example.cutivatingapp1_java.adapter.TaskAdapter;

import com.example.cutivatingapp1_java.databinding.TaskfragmentBinding;
import com.example.cutivatingapp1_java.model.Task;


import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {
    private TaskfragmentBinding binding;

    public TaskFragment() {
        //
    }
    private OnFragmentViewCreatedListener listener;
    public interface OnFragmentViewCreatedListener {
        void onViewCreated(TaskfragmentBinding binding);
    }

    public void setOnFragmentViewCreatedListener(OnFragmentViewCreatedListener listener) {
        this.listener = listener;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //用binding加载布局
        binding=TaskfragmentBinding.inflate(inflater,container,false);

        if (listener != null) {
            listener.onViewCreated(binding); // 👈 Fragment加载完成，通知Activity
        }

        return binding.getRoot();
    }
    //当 View 创建好后，系统会回调这个方法。适合做 View 初始化,点击事件、RecyclerView 设置
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        setListenerToNewFragment(binding.btnAdd,new TaskSubmitFragment());//要传输一个对象

    }

    private void initRecyclerView(){
        //1.向TaskAdapter类中的变量 List<Task> taskList填充数据
        List<Task> taskList=new ArrayList<>();
        taskList.add(new Task("1","计算机","熟练掌握对ui控件的代码编写","2小时30分钟","0小时0分钟"));

        //2.设置 RecyclerView 的布局方式（线性列表）
        binding.taskRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext() ));
        //3.new一个TaskAdapter对象，然后将适配器加到主页面的recycleview里面
        TaskAdapter adapter = new TaskAdapter(requireContext(),taskList);
        binding.taskRecyclerView.setAdapter(adapter);
    }


    private void setListenerToNewFragment(View compose, Fragment targetFragment) {
        compose.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()//requireActivity是获取当前fragment依附的activity,
                    .beginTransaction()
                    .replace(R.id.contentContainer, targetFragment) // MainActivity里的FrameLayout
                    .addToBackStack(null)
                    .commit();
        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 避免内存泄漏
    }
}
