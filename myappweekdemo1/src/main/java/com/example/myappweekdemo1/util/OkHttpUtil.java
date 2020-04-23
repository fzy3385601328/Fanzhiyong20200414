package com.example.myappweekdemo1.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
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
    private static final int GET = 1;
    private static final int POST = 2;
    private Handler handler = new Handler();
    public static final String TAG = "OkHttpUtil";
    private static OkHttpUtil INSTANCE = new OkHttpUtil();
    private final OkHttpClient okHttpClient;

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

    //判断是否联网
    public static boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }
        return false;
    }

    //判断选择哪种请求方式
    public void request(int method, String url, Map<String,String> map, IContract.IModel iModel){
         if (method==GET){
             doGet(url, map, iModel);
         }else {
             doPost(url, map, iModel);
         }
    }


    //Get请求
    public void doGet(String url,Map<String,String>map,IContract.IModel iModel){
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        builder.append("?");

        for (String key:map.keySet()){
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
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,e.getMessage());

                        iModel.onModelError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handler.post(new Runnable() {

                    private String result = null;

                    @Override
                    public void run() {
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
        });
    }

    //Post请求
    public void doPost(String url,Map<String,String>map,IContract.IModel iModel){
        FormBody.Builder formbody = new FormBody.Builder();
        for (String key:map.keySet()){
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
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,e.getMessage());

                        iModel.onModelError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handler.post(new Runnable() {

                    private String result = null;

                    @Override
                    public void run() {
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
        });
    }



}
