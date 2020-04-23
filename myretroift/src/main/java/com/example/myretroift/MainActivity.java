package com.example.myretroift;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myretroift.bean.CircleBean;
import com.example.myretroift.bean.CommodityBean;
import com.example.myretroift.bean.CommodityListBean;
import com.example.myretroift.bean.DataBean;
import com.example.myretroift.bean.JQueryBean;
import com.example.myretroift.bean.LoginBean;
import com.example.myretroift.util.RetroiftUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_view)
    TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login(){
        IRequest iRequest = RetroiftUtil.getInstance().create(IRequest.class);
        iRequest.login("13343239555","bilibili123").enqueue(new Callback<DataBean<LoginBean>>() {
            @Override
            public void onResponse(Call<DataBean<LoginBean>> call, final Response<DataBean<LoginBean>> response) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         DataBean<LoginBean> loginbean = response.body();
                         textView.setText(new Gson().toJson(loginbean));
                     }
                 });
            }

            @Override
            public void onFailure(Call<DataBean<LoginBean>> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.list_query)
    public void list(){
        IRequest iRequest = RetroiftUtil.getInstance().create(IRequest.class);
        iRequest.commodityList().enqueue(new Callback<DataBean<CommodityListBean>>() {
            @Override
            public void onResponse(Call<DataBean<CommodityListBean>> call, Response<DataBean<CommodityListBean>> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        DataBean<CommodityListBean> body = response.body();
                        textView.setText(new Gson().toJson(body));
                    }
                });
            }

            @Override
            public void onFailure(Call<DataBean<CommodityListBean>> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.query_shop)
    public void shop(){
        IRequest iRequest = RetroiftUtil.getInstance().create(IRequest.class);
        iRequest.findShoppingCart("34916","158754827621834916").enqueue(new Callback<JQueryBean>() {
            @Override
            public void onResponse(Call<JQueryBean> call, final Response<JQueryBean> response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        JQueryBean jquery = response.body();
                        textView.setText(new Gson().toJson(jquery));
                    }
                });
            }

            @Override
            public void onFailure(Call<JQueryBean> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btn_circle)
    public void circle(){
        IRequest iRequest = RetroiftUtil.getInstance().create(IRequest.class);
        iRequest.findCircleList("1","5").enqueue(new Callback<CircleBean.ResultBean>() {
            @Override
            public void onResponse(Call<CircleBean.ResultBean> call, Response<CircleBean.ResultBean> response) {
                CircleBean.ResultBean circle = response.body();
                textView.setText(new Gson().toJson(circle));
            }

            @Override
            public void onFailure(Call<CircleBean.ResultBean> call, Throwable t) {

            }
        });
    }
}
