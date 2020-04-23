package com.example.fanzhiyong20200414.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fanzhiyong20200414.R;
import com.example.fanzhiyong20200414.bean.LoginBean;
import com.example.fanzhiyong20200414.core.BaseActivity;
import com.example.fanzhiyong20200414.core.BasePresenter;
import com.example.fanzhiyong20200414.presenter.LoginPresenter;
import com.example.fanzhiyong20200414.util.IContract;
import com.example.fanzhiyong20200414.util.VolleyUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements IContract.IView {

    private EditText editPhone;
    private EditText editPwd;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //找控件
        editPhone = findViewById(R.id.edit_phone);
        editPwd = findViewById(R.id.edit_pwd);

        //点击事件
        //点击事件
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收集用户输入的数据
                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();
                //对用户输入的数据进行判断
                if (!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(pwd)){
                    //使用Map集合存储数据
                    Map<String,String> map = new HashMap<>();

                    map.put("phone",phone);
                    map.put("pwd",pwd);

                    //判断是否存在网络
                    if (VolleyUtil.isActivice(getBaseContext())){
                        //成功后发送数据
                        mPresenter.request(map);
                    }else {
                        //失败后吐司没网
                        Toast.makeText(LoginActivity.this, "无网络状态", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "请输入正确的用户名或者密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onViewSuccess(String json) {
        //获取成功打印数据
        Log.i("fzy",json);

        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);

        //判断成功后
        if (loginBean.getStatus().equals("0000")){
            //跳转到搜索页面
            Intent intent = new Intent(getBaseContext(), SearchActivity.class);
            startActivity(intent);
            finish();//跳转成功后关闭本页面
        }else {
            Toast.makeText(this, loginBean.getMessage()+""+loginBean.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewError(String msg) {
        //失败后打印数据
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
