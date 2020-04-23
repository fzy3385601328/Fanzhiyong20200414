package com.example.myfan20200420.presenter;

import com.example.myfan20200420.core.BasePresenter;
import com.example.myfan20200420.util.IContract;
import com.example.myfan20200420.util.OkHttpUtil;

public class RxxpPresenter extends BasePresenter {

    public RxxpPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return OkHttpUtil.GET;
    }

    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    }
}
