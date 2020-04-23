package com.example.myfan20200420.bean;

import java.util.List;

public class User<T> {
    public ResultsBean result;
    public String message;
    public String status;

    public class ResultsBean{
        public ResultInfo rxxp;
        public ResultInfo mlss;
        public ResultInfo pzsh;
    }
}
