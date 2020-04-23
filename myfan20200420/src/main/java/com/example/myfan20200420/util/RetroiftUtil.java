package com.example.myfan20200420.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//RetroiftUtil工具类
public class RetroiftUtil {

    //RetroiftUtil单例模式
    private static RetroiftUtil retroiftUtil = new RetroiftUtil();
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private RetroiftUtil(){

        //Http网络请求
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetroiftUtil getInstance(){
        return retroiftUtil;
    }

    //重写RetroiftUtil的方法
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


}
