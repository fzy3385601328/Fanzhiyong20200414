package com.example.myweekonedemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myweekonedemo.R;
import com.example.myweekonedemo.bean.User;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    //注册控件(使用EventBus)
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);//注册
    }

    @OnClick(R.id.btn_login)
    public void post(){
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        //使用EventBus粘性事件发送
        //普通的事件
        EventBus.getDefault().postSticky(phone);
        //这个是粘性事件
        EventBus.getDefault().postSticky(100);

        EventBus.getDefault().postSticky(new User(1,"dingtao"));

        //发送消息
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }



}
