package com.example.cutivatingapp1_java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cutivatingapp1_java.databinding.MainviewBinding;
import com.example.cutivatingapp1_java.databinding.TaskfragmentBinding;
import com.example.cutivatingapp1_java.fragment.ForumFragment;
import com.example.cutivatingapp1_java.fragment.GrownFragment;
import com.example.cutivatingapp1_java.fragment.TaskDetailFragment;
import com.example.cutivatingapp1_java.fragment.TaskFragment;
import com.example.cutivatingapp1_java.fragment.TaskSubmitFragment;
import com.example.cutivatingapp1_java.fragment.TaskTimeFragment;
import com.example.cutivatingapp1_java.interfaces.HasTopBar;


public class MainAcitivity extends AppCompatActivity {
    private MainviewBinding binding;
    private TaskfragmentBinding taskBing;
    private TaskFragment taskfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initSystemBar();
        PopMenu();//主侧边导航栏

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                cheakMainFragment(); // 每次返回栈变化，就去检查当前fragment，更新标题栏和底栏
            }
        });
        switchToMainFragment(new TaskFragment());
        setupBottomNavigation();

    }
    @Override
    public void onBackPressed() {
        Log.d("点击返回键了", "onBackPressed: ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // 从返回栈弹出上一个 fragment
        }else {
            super.onBackPressed();
            Log.d("返回键", "像系统一样返回");
        }
}



    private void initView(){
        //setcontent
        binding = MainviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // ✅ 用 binding 的 root 设置视图
    }
    private void initSystemBar() {
        Window window = getWindow();

        // 不给系统窗口留空间，实现沉浸式
        window.setDecorFitsSystemWindows(false);

        // 透明状态栏
        window.setStatusBarColor(android.graphics.Color.TRANSPARENT);

        // 设置状态栏图标为黑色（浅色背景用黑色图标）
        View decorView = window.getDecorView();
        WindowInsetsController controller = decorView.getWindowInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            );
        }


        // 动态给标题栏加上状态栏高度
        int statusBarHeight = getStatusBarHeight();
        binding.titleLayout.setPadding(0, statusBarHeight, 0, 0);
    }


    // 获取状态栏高度的方法
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment;
            if(item.getItemId()==R.id.Task){
                fragment = new TaskFragment();
            } else if (item.getItemId()==R.id.Game) {
                fragment = new GrownFragment();
            } else if (item.getItemId()==R.id.Grown) {
                fragment = new ForumFragment();
            }else {
                return false;
            }

            switchToMainFragment(fragment);
            return true;
        });
    }


    //设置系统栏能根据不同的页面显示不同的页面
    private void setTopTitleView( FrameLayout container,View viewToAdd){
        if (container != null && viewToAdd != null) {
            // 重点！！如果 view 已经有父了，要先把它从老父亲那里拿下来
            if (viewToAdd.getParent() != null) {
                ((ViewGroup) viewToAdd.getParent()).removeView(viewToAdd);
            }
            container.removeAllViews(); // 清空现有视图
            container.addView(viewToAdd);
        }
    }
    //算了还是考虑一下这个要不要吧
    private void switchToMainFragment(Fragment fragment) {
        if (fragment instanceof HasTopBar) {
            HasTopBar barFragment = (HasTopBar) fragment;

            // 注册回调，等待视图准备好后再设置 TopBar
            barFragment.setTopBarReadyListener((topBarView, title) -> {
                setTopTitleView(binding.titleRightContainer, topBarView);
                binding.titleBar.setText(title);
            });
        } else {
            // 没有实现 HasTopBar，清除 TopBar
            binding.titleRightContainer.removeAllViews();
            binding.titleBar.setText("");
        }

        // 切换 fragment（不能提前访问 binding，否则可能崩溃）
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .commit();
    }

    private void cheakMainFragment(){ //方法名应该改成查看mainactivy中的返回栈中的fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        Log.d("返回栈数量", "当前数量: " + count);

        if (count == 0) {
            Log.d("返回栈空了", "finish activity");
            finish();
            return;
        }

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.contentContainer);
        if(currentFragment!=null){
            Log.d("点击的fragment是", currentFragment.getClass().getSimpleName());
        }
//instanceof时判断是否相等的，
        if (currentFragment instanceof TaskFragment ) {
            binding.titleLayout.setVisibility(View.VISIBLE);
            binding.bottomNavigation.setVisibility(View.VISIBLE);
        } else {
            // 如果当前还是其他子Fragment，就隐藏标题栏和底栏（可选，看你的设计）
            binding.titleLayout.setVisibility(View.GONE);
            binding.bottomNavigation.setVisibility(View.GONE);
        }

    }

    private void switchFragment(Fragment targetFragment){
        // 1. 替换内容区
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentContainer, targetFragment)
                .addToBackStack(targetFragment.getClass().getSimpleName()) // 加到返回栈，按返回键可以回到主界面
                .commit();
    }
    private void PopMenu() {
        binding.btnMenu.setOnClickListener(v -> { // 普通点击就打开/关闭
            Log.d("PopMenu啊啊啊啊啊啊啊", "Button clicked");
            if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START);  // 如果侧边栏已经打开，就关闭
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START);   // 没打开就打开
            }
        });

        binding.navigationView.setNavigationItemSelectedListener(item -> { // 这里应该用binding
            int id = item.getItemId();
            if (id == R.id.menu_timer_page) {
                switchFragment(new TaskTimeFragment());

            } else if (id == R.id.menu_analysis_page) {
                Log.d("进入数据分析页面", "Button clicked");
                switchFragment(new TaskDetailFragment());

            } else if (id == R.id.menu_submit_page) {
                Log.d("进入提交页面", "Button clicked");
                switchFragment(new TaskSubmitFragment());

            } else if (id == R.id.menu_completed_page) {
                // 跳转任务完成页面
            } else if (id == R.id.menu_nurture_module) {
                // 养成模块
            } else if (id == R.id.menu_forum_main) {
                // 论坛主页
            } else if (id == R.id.menu_forum_hot) {
                // 热点论坛
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START); // 选中之后关闭侧边栏
            return true;
        });
    }







}

