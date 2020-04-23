package com.example.myfan20200420.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfan20200420.presenter.ResultPresenter;
import com.example.myfan20200420.presenter.RxxpPresenter;

public abstract class BaseActivity extends AppCompatActivity {

    public BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();

        setContentView(getLayoutResId());

        initView(savedInstanceState);
    }

    protected abstract RxxpPresenter initPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.destory();
            mPresenter=null;
        }
    }
}
