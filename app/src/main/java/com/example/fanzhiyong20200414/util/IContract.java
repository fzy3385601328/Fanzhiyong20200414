package com.example.fanzhiyong20200414.util;
//契约类,管理所有类的接口
public class IContract {
    //定义V层接口
    public interface IView{
        //定义V层成功或者失败的方法
        void onViewSuccess(String json);
        void onViewError(String msg);
    }
    //定义M层接口
    public interface IModel{
        //定义M层成功或者失败的方法
        void onModelSuccess(String json);
        void onModelError(String msg);
    }
}
