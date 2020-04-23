package com.example.fanzhiyong20200414.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fanzhiyong20200414.core.MyApp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * VolleyUtil网络工具类
 */
public class VolleyUtil {
    //创建标示符
    private static final String TAG = "VolleyUtil";
    //单例模式
    private final static VolleyUtil INSTANCE = new VolleyUtil();

    //请求队列
    private static RequestQueue eQueue = Volley.newRequestQueue(MyApp.getContext());

    private VolleyUtil(){

    }

    public static VolleyUtil getInstance(){
        return INSTANCE;
    }



    //判断网络状态
    public static boolean isActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            //有网状态
            return true;
        }
        return false;
    }

    //判断发送请求的方式
    public void request(int method,String url,Map<String,String>map,IContract.IModel iModel){
        if (method==Request.Method.GET){
            doGet(url, map, iModel);
        }else {
            doPost(url, map, iModel);
        }
    }

    //get方法
    public void doGet(String url, Map<String,String> map, final IContract.IModel iModel){
        //因为接口中含有参数，所以需要拼接参数
        StringBuilder builder = new StringBuilder();
        //追加数据
        builder.append("?");

        //遍历数据
        for(String key:map.keySet()){
            //追加数据
            try {
                String value = URLEncoder.encode(map.get(key), "utf-8");
                //再次追加数据
                builder.append(key+"="+value+"&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        //截取字符串数据
        String permas = builder.substring(0, builder.length() - 1);
        //Log.i
        Log.i(TAG,permas);

        //发送字符串请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + permas
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //打印成功的方法
                Log.i(TAG,response);
                iModel.onModelSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               //打印失败的方法
                Log.i(TAG,error.getMessage());
                iModel.onModelError(error.getMessage());
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String a;
                try {
                    a=new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    a=new String(response.data);
                }
                return Response.success(a, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        eQueue.add(stringRequest);
    }

    //post方法
    public void doPost(String url,final Map<String,String>map,final IContract.IModel iModel){
        //发送字符串请求
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //打印成功的方法
                Log.i(TAG,response);
                iModel.onModelSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //打印失败的方法
                Log.i(TAG,error.getMessage());
                iModel.onModelError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String a;
                try {
                    a=new String(response.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    a=new String(response.data);
                }
                return Response.success(a, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        eQueue.add(stringRequest);
    }
}
