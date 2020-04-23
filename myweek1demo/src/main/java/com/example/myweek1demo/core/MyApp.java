package com.example.myweek1demo.core;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static Context getmContext() {
        return mContext;
    }
}
