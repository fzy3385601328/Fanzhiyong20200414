package com.example.two_dimensioncode1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttp实现的思路
 */

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static final String TAG = "MainActivity";
    private TextView textView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        //checkSelfPermission方法就是检测是否有权限
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            //拥有权限
        }else {
            //没有权限，申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},200);
        }

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建OkHttp的拦截控制器
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                //设置日志级别
                interceptor.level(HttpLoggingInterceptor.Level.BODY);
                //使用建造者模式建造对象
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();

                //创建request构建对象
                Request builder = new Request.Builder()
                        .url("http://mobile.bwstudent.com/small/commodity/" +
                                "v1/findCommodityByKeyword?keyword=女鞋&page=1&count=5")
                        .get()
                        .build();

                //构建call对象,进行发送get和post请求
                Call call = client.newCall(builder);
                //发送异步请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i("onFailuer",e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                         //获取发送结果
                        String result = response.body().string();

                        Message obtain = Message.obtain();
                        obtain.obj=result;
                        handler.sendMessage(obtain);
                    }
                });
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建OkHttp的拦截控制器
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                //设置日志级别
                interceptor.level(HttpLoggingInterceptor.Level.BODY);
                //使用建造者模式建造对象
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();

                //创建存储数据对象
                FormBody body = new FormBody.Builder()
                        .add("phone", "18831985600")
                        .add("pwd", "xiaofan123")
                        .build();

                //创建request构建对象
                Request builder = new Request.Builder()
                        .url("http://mobile.bwstudent.com/small/user/v1/login")
                        .post(body)
                        .build();

                //构建call对象,进行发送get和post请求
                Call call = client.newCall(builder);
                //发送异步请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i("onFailuer",e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        //获取发送结果
                        String result = response.body().string();

                        Message obtain = Message.obtain();
                        obtain.obj=result;
                        handler.sendMessage(obtain);
                    }
                });
            }
        });

        

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
