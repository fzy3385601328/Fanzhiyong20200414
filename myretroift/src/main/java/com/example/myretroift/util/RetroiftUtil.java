package com.example.myretroift.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroiftUtil {

    private static RetroiftUtil retroiftUtil = new RetroiftUtil();
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private RetroiftUtil(){
        //OkHttp拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetroiftUtil getInstance(){
        return retroiftUtil;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


}
