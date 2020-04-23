package com.example.myweekonedemo.core;

import com.example.myweekonedemo.util.IContract;
import com.example.myweekonedemo.util.OkHttpUtil;

import java.util.Map;

public abstract class BasePresenter {
    private IContract.IView iView;

    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
    }

    public void request(Map<String,String>map){
        OkHttpUtil.getInstance().request(getMethod(), getUrl(), map, new IContract.IModel() {
            @Override
            public void onModelSuccess(String json) {
                iView.onViewSuccess(json);
            }

            @Override
            public void onModelError(String error) {
                iView.onViewError(error);
            }
        });
    }

    protected abstract int getMethod();
    protected abstract String getUrl();

    public void destory(){
        if (iView!=null){
            iView=null;
        }
    }
}
