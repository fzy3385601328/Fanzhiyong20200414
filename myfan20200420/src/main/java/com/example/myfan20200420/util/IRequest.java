package com.example.myfan20200420.util;

import com.example.myfan20200420.bean.ResultInfo;
import com.example.myfan20200420.bean.ResultListBean;
import com.example.myfan20200420.bean.User;
import com.example.myfan20200420.bean.Users;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRequest {

    //接口数据请求
    //http://mobile.bwstudent.com/small/commodity/v1/commodityList
    @GET("small/commodity/v1/commodityList")
    Call<User.ResultsBean> result();



}
