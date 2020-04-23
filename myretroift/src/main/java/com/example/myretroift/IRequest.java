package com.example.myretroift;

import com.example.myretroift.bean.CircleBean;
import com.example.myretroift.bean.CommodityBean;
import com.example.myretroift.bean.CommodityListBean;
import com.example.myretroift.bean.DataBean;
import com.example.myretroift.bean.JQueryBean;
import com.example.myretroift.bean.LoginBean;
import com.example.myretroift.bean.ResultBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {

    //http://mobile.bwstudent.com/small/commodity/v1/commodityList
    @GET("small/commodity/v1/commodityList")
    Call<DataBean<CommodityListBean>> commodityList();

    //http://mobile.bwstudent.com/small/user/v1/login?phone=13343239555&pwd=bilibili123
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Call<DataBean<LoginBean>> login(@Field("phone")String phone,@Field("pwd")String pwd);

    //http://mobile.bwstudent.com/small/order/verify/v1/findShoppingCart?userId=34916&sessionId=158754827621834916
    @GET("small/order/verify/v1/findShoppingCart")
    Call<JQueryBean>findShoppingCart(@Query("userId")String userId,@Query("session")String sessionId);

    //http://mobile.bwstudent.com/small/circle/v1/findCircleList?page=1&count=5
    @GET("small/circle/v1/findCircleList?page=1&count=5")
    Call<CircleBean.ResultBean> findCircleList(@Query("page")String page,
                                               @Query("count")String count);

}
