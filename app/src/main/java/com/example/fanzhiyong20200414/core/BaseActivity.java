package com.example.fanzhiyong20200414.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();

        setContentView(getLayoutResId());

        initView(savedInstanceState);
    }

    protected abstract BasePresenter initPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    //调用BasePresenter的防止内存泄露方法

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.destory();
            mPresenter=null;
        }
    }
}
