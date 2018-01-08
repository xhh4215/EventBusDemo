package com.example.xhh.eventbusdemo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by xhh on 2018/1/3.
 */

public class MyApplication extends Application {
    private   static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
