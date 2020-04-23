package com.example.myfan20200420.util;
//MVP架构中的契约类
public interface IContract {
    //处理V层接口
    interface IView{
        //V层回调成功的方法
        void onViewSuccess(String json);
        //V层回调失败的方法
        void onViewError(String msg);
    }

    //处理M层接口
    interface IModel{
        //V层回调成功的方法
        void onModelSuccess(String json);
        //V层回调失败的方法
        void onModelError(String msg);
    }

}
