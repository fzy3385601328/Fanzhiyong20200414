package com.example.two_dimensioncode1.core;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
    }
}
