package com.example.myfan20200420.core;

import com.example.myfan20200420.util.IContract;
import com.example.myfan20200420.util.OkHttpUtil;

import java.util.Map;

public abstract class BasePresenter {
    public IContract.IView iView;

    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
    }

    public void request(){
        //使用OKHttp网络请求数据
        OkHttpUtil.getInstance().request(getMethod(), getUrl(), new IContract.IModel() {
            @Override
            public void onModelSuccess(String json) {
                iView.onViewSuccess(json);
            }

            @Override
            public void onModelError(String msg) {
                iView.onViewError(msg);
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
