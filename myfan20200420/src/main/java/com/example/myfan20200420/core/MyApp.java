package com.example.myfan20200420.core;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}
