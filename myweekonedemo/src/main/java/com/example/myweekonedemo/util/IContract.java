package com.example.myweekonedemo.util;
//契约类
public interface IContract {
    //V层接口
    interface IView{
        void onViewSuccess(String json);
        void onViewError(String error);
    }

    //M层接口
    interface IModel{
        void onModelSuccess(String json);
        void onModelError(String error);
    }
}
