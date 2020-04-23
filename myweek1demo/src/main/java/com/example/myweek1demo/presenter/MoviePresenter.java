package com.example.myweek1demo.presenter;

import com.example.myweek1demo.core.BasePresenter;
import com.example.myweek1demo.util.IContract;
import com.example.myweek1demo.util.OkHttpUtil;

public class MoviePresenter extends BasePresenter {

    public MoviePresenter(IContract.IView iView) {
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
