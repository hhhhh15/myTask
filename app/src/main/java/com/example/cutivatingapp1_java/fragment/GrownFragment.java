package com.example.cutivatingapp1_java.fragment;

import android.view.View;

import com.example.cutivatingapp1_java.interfaces.HasTopBar;

public class GrowUpFragmrnt implements HasTopBar {
    private GrowUpFragmrnt binding;
    @Override
    public View getTopBarView() {
        return null; // 你的右上角按钮
    }

    @Override
    public String getTopTitle() {
        return "养成模块";
    }

}
