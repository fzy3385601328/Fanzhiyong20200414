package com.example.myweek1demo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    public static final int GET = 1;
    public static final int POST = 2;
    //单例模式
    private static OkHttpUtil INSTANCE = new OkHttpUtil();
    private final OkHttpClient okHttpClient;
    private static final String TAG = "OkHttpUtil";

    private OkHttpUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static OkHttpUtil getInstance(){
        return INSTANCE;
    }

    public static boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }
        return false;
    }

    public void request(int method,String url,Map<String,String>map,IContract.IModel iModel){
        if (method==GET){
            doGet(url, map, iModel);
        }else {
            doPost(url, map, iModel);
        }
    }

    public void doGet(String url, Map<String,String>map,IContract.IModel iModel){
        StringBuilder builder = new StringBuilder();
        builder.append("?");

        for (String key:map.keySet()) {
            try {
                String value = URLEncoder.encode(map.get(key), "utf-8");
                builder.append(key+"="+value+"&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        String parems = builder.substring(0, builder.length() - 1);
        Log.i(TAG,parems);

        Request request = new Request.Builder()
                .url(url + parems)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iModel.onModelError(e.getMessage());
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response){
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,Thread.currentThread().getName());
                iModel.onModelSuccess(result);
            }
        });
    }

    public void doPost(String url,Map<String,String>map,IContract.IModel iModel){
        FormBody.Builder formbody = new FormBody.Builder();
        for (String key:
             map.keySet()) {
            formbody.add(key,map.get(key));
        }

        Request request = new Request.Builder()
                .url(url)
                .post(formbody.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                iModel.onModelError(e.getMessage());
                Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response){
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,Thread.currentThread().getName());
                iModel.onModelSuccess(result);
            }
        });
    }
}
