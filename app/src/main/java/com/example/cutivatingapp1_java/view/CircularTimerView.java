package com.example.cutivatingapp1_java.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

public class CircularTimerView extends View {//父类如果无参函数，可以不用写，但是如果是有参的，必须调用，并写参数进去
    private Paint circlePaint;
    private Paint textPaint;


    public  CircularTimerView(Context context){
        super(context);
    }
//设置画笔
    public void initPaint(){
        circlePaint=new Paint();
        circlePaint.setColor();

        textPaint=new Paint();
    }
}
