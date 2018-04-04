package com.example.diplomaproject;

import android.app.Application;
import android.content.Context;


import com.example.diplomaproject.utils.util.AppContextUtil;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyApplication extends Application {
    public static Context applicationContext;
    public static Context getContext() {
        return applicationContext;
    }




    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtil.init(this);

        Bmob.initialize(this, "14789ddb92555dba0d04055ed8921c59");
        applicationContext = this;
    }
}

