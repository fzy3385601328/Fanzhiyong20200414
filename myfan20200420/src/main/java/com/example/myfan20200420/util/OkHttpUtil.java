package com.example.myfan20200420.util;

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
    public static final int GET = 1;//GET请求
    public static final int POST = 2;//POST请求
    //写入OKHttp的饿汉型单例模式
    private static OkHttpUtil okHttpUtil = new OkHttpUtil();
    //设置标示
    private static final String TAG = "OkHttpUtil";

    //创建Handler对象
    private Handler handler = new Handler();
    private final OkHttpClient okHttpClient;

    private OkHttpUtil(){
        //在构造方法中写入日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);//在拦截器中进行模式的选择
        //创建建造者模式
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.SECONDS)//响应的时间
                .readTimeout(5000, TimeUnit.MILLISECONDS).//读取的时间超时
                writeTimeout(5000, TimeUnit.MILLISECONDS)//写入的时间超时
                .build();
    }

    //返回当前的单例模式
    public static OkHttpUtil getInstance(){
        return okHttpUtil;
    }

    //网络判断
    public static boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }
        return false;
    }

    public void request(int method, String url, IContract.IModel iModel) {
        //如果是get请求
        doGet(url, iModel);
    }

    //判断网络请求的方法
    public void request(int method,String url,Map<String,String>map,IContract.IModel iModel){
        //如果是get请求
        if (method==GET){
            //如果是GET请求
            doGet(url, iModel);
        }else {
            //否则就是Post请求
            doPost(url, map, iModel);
        }
    }

    //创建get请求
    public void doGet(String url, final IContract.IModel iModel){
        /*//拼接参数注:这块是在有参数的情况下写入，如果接口没有参数可以不写
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

        String parems = builder.substring(0, builder.length() - 1);*/

        //重构Request请求
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);//存入缓存数据
        //通过Call对象来同步数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                //通过Handler的post方式发送请求
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iModel.onModelError(e.getMessage());
                        //把错误信息打印出来
                        Log.i(TAG,e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
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

    //创建get请求
    public void doPost(String url, Map<String,String> map, final IContract.IModel iModel){
        //创建FromBody对象
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key:map.keySet()) {
            formBody.add(key,map.get(key));
        }

        //重构Request请求
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();

        Call call = okHttpClient.newCall(request);//存入缓存数据
        //通过Call对象来同步数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                //通过Handler的post方式发送请求
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iModel.onModelError(e.getMessage());
                        //把错误信息打印出来
                        Log.i(TAG,e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
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
