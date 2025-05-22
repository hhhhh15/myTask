package com.example.cutivatingapp1_java.utils;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowInsetsController;


public class StatusBarUtil {

    public static void enableImmersiveMode(Activity activity, View titleView) {

            activity.getWindow().setDecorFitsSystemWindows(false);

            WindowInsetsController controller = activity.getWindow().getInsetsController();
            if (controller != null) {
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }

            // 自动添加 paddingTop 防止被遮挡
            int statusBarHeight = getStatusBarHeight(activity);
            titleView.setPadding(
                    titleView.getPaddingLeft(),
                    statusBarHeight,
                    titleView.getPaddingRight(),
                    titleView.getPaddingBottom()
            );
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
