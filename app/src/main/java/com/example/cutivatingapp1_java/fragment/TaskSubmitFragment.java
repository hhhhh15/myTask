package com.example.cutivatingapp1_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cutivatingapp1_java.databinding.SubmitTaskBinding;
import com.example.cutivatingapp1_java.databinding.TaskfragmentBinding;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskSubmitFragment extends Fragment {
    private SubmitTaskBinding binding;
    //加载布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //用binding加载布局
        binding= SubmitTaskBinding.inflate(inflater,container,false);//这个container需要改变
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectCalendar(binding.tvStartTime);
        selectCalendar(binding.tvEndTime);
    }
    //选择任务开始、结束时间
    private void selectCalendar(TextView textView){
        textView.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("选择日期")
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());   //获取选中的日期
                String formattedDate = sdf.format(new Date(selection));             //格式化日期

                textView.setText(formattedDate);
            });
            datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");

        });
    }



}
