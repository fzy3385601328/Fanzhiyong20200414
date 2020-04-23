package com.example.myfan20200420.presenter;

import com.example.myfan20200420.core.BasePresenter;
import com.example.myfan20200420.util.IContract;

public class BannerPresenter extends BasePresenter {

    public BannerPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return 0;
    }

    @Override
    protected String getUrl() {
        return null;
    }
}
