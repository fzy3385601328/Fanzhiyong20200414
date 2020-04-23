package com.example.fanzhiyong20200414.core;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    //创建静态的上下文对象
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //引入异常信息类
        Thread.setDefaultUncaughtExceptionHandler(new MyException());
    }

    public static Context getContext() {
        return context;
    }
}
