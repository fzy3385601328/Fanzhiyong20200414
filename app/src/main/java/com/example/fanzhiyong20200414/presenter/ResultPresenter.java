package com.example.fanzhiyong20200414.presenter;

import com.android.volley.Request;
import com.example.fanzhiyong20200414.core.BasePresenter;
import com.example.fanzhiyong20200414.util.IContract;

public class ResultPresenter extends BasePresenter {
    public ResultPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return Request.Method.GET;
    }

    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
    }
}
