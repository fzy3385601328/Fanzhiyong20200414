package com.example.fanzhiyong20200414.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
          //1.将字符串转换为时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);

        //获取最新的版本号
        String OnsVersion = Build.VERSION.SDK_INT + "";
        //获取最新的版本名
        String AppVersion = packageCode(MyApp.getContext()) + " " + packageCode(MyApp.getContext());
        //获取异常信息并打印
        String error = e.getMessage();

        //打印信息如下
        Log.i("fzy","\n时间"+time+"\n获取最新的版本号"+OnsVersion+"\n获取最新的版本名"+AppVersion+
                "\n打印具体信息"+error);
    }

    //获取应用版本号
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    //获取应用版本名
    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }
}
