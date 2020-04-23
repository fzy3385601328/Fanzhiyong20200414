package com.example.myweekonedemo.presenter;

import com.example.myweekonedemo.core.BasePresenter;
import com.example.myweekonedemo.util.IContract;
import com.example.myweekonedemo.util.OkHttpUtil;

public class LoginPresenter extends BasePresenter {

    public LoginPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return OkHttpUtil.POST;
    }

    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/user/v1/login";
    }
}
