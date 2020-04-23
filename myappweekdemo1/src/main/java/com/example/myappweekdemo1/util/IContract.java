package com.example.myappweekdemo1.util;

public interface IContract {

    interface IView{
        void onViewSuccess(String json);
        void onViewError(String msg);
    }

    interface IModel{
        void onModelSuccess(String json);
        void onModelError(String msg);
    }
}
