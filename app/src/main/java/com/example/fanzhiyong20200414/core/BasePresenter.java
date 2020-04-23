package com.example.fanzhiyong20200414.core;

import com.example.fanzhiyong20200414.util.IContract;
import com.example.fanzhiyong20200414.util.VolleyUtil;

import java.util.Map;

public abstract class BasePresenter {
    public IContract.IView iView;

    public BasePresenter(IContract.IView iView) {
        this.iView = iView;
    }

    public void request(Map<String,String> map){
        //调用VolleyUtil的方法
        VolleyUtil.getInstance().request(getMethod(), getUrl(), map, new IContract.IModel() {
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

    //解决内存泄露的方案
    public void destory(){
        if (iView!=null){
            iView=null;
        }
    }
}
