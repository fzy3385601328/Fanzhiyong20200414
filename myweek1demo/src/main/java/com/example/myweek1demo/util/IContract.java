package com.example.myweek1demo.util;

public class IContract {

    public interface IView{
        void onViewSuccess(String json);
        void onViewError(String msg);
    }

    public interface IModel{
        void onModelSuccess(String json);
        void onModelError(String error);
    }
}
