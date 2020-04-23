package com.example.fanzhiyong20200414.presenter;

import com.android.volley.Request;
import com.example.fanzhiyong20200414.core.BasePresenter;
import com.example.fanzhiyong20200414.util.IContract;

/**
 * 登录页面的Presenter
 */
public class LoginPresenter extends BasePresenter {
    public LoginPresenter(IContract.IView iView) {
        super(iView);
    }

    @Override
    protected int getMethod() {
        return Request.Method.POST;
    }

    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/user/v1/login";
    }
}
