package com.example.myweekonedemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.myweekonedemo.R;
import com.example.myweekonedemo.bean.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.spec.ECField;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.text_phone)
    TextView text_phone;
    @BindView(R.id.text_pwd)
    TextView text_pwd;
    @BindView(R.id.btn_tell)
    Button btn_tell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定视图
        ButterKnife.bind(this);

        //注册信息
        EventBus.getDefault().register(this);

    }

    @OnClick({R.id.btn_tell})
    public void post(){
        EventBus.getDefault().post("本页面传值了");
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void postPhone(String text){
        Log.i(TAG,text);
        text_phone.setText(text);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void postPwd(String text){
        Log.i(TAG,text);
        text_pwd.setText(text);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void postText(Integer text){
        Log.i(TAG,text+"");

    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void postText(User text){
        Log.i(TAG,text.id+" "+text.name);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
