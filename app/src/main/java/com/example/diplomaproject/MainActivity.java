package com.example.diplomaproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diplomaproject.ui.base.BaseActivity;
import com.example.diplomaproject.ui.base.BaseFragment;
import com.example.diplomaproject.ui.fragment.HomeFragment;
import com.example.diplomaproject.ui.fragment.TabFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class MainActivity extends BaseActivity{

    @Override
    protected int getContextViewId() {
        return R.id.qmuidemo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        QMUIStatusBarHelper.setStatusBarLightMode(getBaseFragmentActivity());
//        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        if (savedInstanceState == null) {
//            BaseFragment fragment = new HomeFragment();
            BaseFragment fragment = new TabFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

}
