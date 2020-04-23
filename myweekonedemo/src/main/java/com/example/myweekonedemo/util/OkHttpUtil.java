package com.example.myweekonedemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.textclassifier.TextLinks;

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

/**
 * okHttp的网络工具类的封装
 * 1.配置OkHttp依赖
 * 2.
 */
public class OkHttpUtil {

    public static final int GET = 1;
    public static final int POST = 2;
    private static OkHttpUtil INSTANCE = new OkHttpUtil();
    private final OkHttpClient okHttpClient;
    private static final String TAG = "OkHttpUtil";
    private Handler handler = new Handler();


    private OkHttpUtil(){
        //创建OkHttp的构造拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        //使用建造者模式构造对象
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static OkHttpUtil getInstance(){
        return INSTANCE;
    }

    private static boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }
        return false;
    }

    public void request(int method,String url,final Map<String, String> params,
                        final IContract.IModel iModel){
        if (method == GET) {//判断是不是get,如果是则调用get方法，不是的话则调用post方法
            doGet(url,params,iModel);
        }else{
            doPost(url,params,iModel);
        }
    }

    private void doGet(String url,Map<String,String>map,IContract.IModel iModel){
        StringBuilder builder = new StringBuilder();
        builder.append("?");//拼接字符串
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

        final Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //失败的方法
                        iModel.onModelError(e.getMessage());
                        Log.i(TAG,e.getMessage());
                    }
                });


            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //请求成功的方法
                handler.post(new Runnable() {

                    private String result;

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

    private void doPost(String url,Map<String,String>map,IContract.IModel iModel){

        FormBody.Builder formbody = new FormBody.Builder();
        for (String key:map.keySet()){
            formbody.add(key,map.get(key));
        }

        Request build = new Request.Builder()
                .url(url)
                .post(formbody.build())
                .build();

        final Call call = okHttpClient.newCall(build);

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

                    private String result;

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
