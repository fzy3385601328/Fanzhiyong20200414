package com.example.myweekonedemo.bean;

import com.example.myweekonedemo.core.BasePresenter;
import com.example.myweekonedemo.util.IContract;
import com.example.myweekonedemo.util.OkHttpUtil;

public class ResutlPresenter extends BasePresenter {
    public ResutlPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return OkHttpUtil.GET;
    }

    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/movieApi/movie/v2/findHotMovieList?page=1&count=10";
    }
}
