package com.example.myfan20200420.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfan20200420.R;
import com.example.myfan20200420.adapter.RxxpAdapter;
import com.example.myfan20200420.bean.ResultInfo;
import com.example.myfan20200420.bean.ResultListBean;
import com.example.myfan20200420.bean.User;
import com.example.myfan20200420.bean.Users;
import com.example.myfan20200420.core.BaseActivity;
import com.example.myfan20200420.presenter.ResultPresenter;
import com.example.myfan20200420.presenter.RxxpPresenter;
import com.example.myfan20200420.util.IContract;
import com.example.myfan20200420.util.IRequest;
import com.example.myfan20200420.util.OkHttpUtil;
import com.example.myfan20200420.util.RetroiftUtil;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends BaseActivity implements IContract.IView {
    private static final String TAG = "Main2Activity";
    @BindView(R.id.btn_query)
    Button btn_query;
    @BindView(R.id.text_show)
    Button text_show;

    @BindView(R.id.recycler_v2)
    RecyclerView reycler_v2;
    private RxxpAdapter rxxpAdapter;
    private Handler handler = new Handler();


    @Override
    protected RxxpPresenter initPresenter() {
        return new RxxpPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (Main2Activity.this, RecyclerView.HORIZONTAL, false);
        reycler_v2.setLayoutManager(linearLayoutManager);

        rxxpAdapter = new RxxpAdapter();
        reycler_v2.setAdapter(rxxpAdapter);

        if (OkHttpUtil.isNetActivice(this)){
            //有网络就请求
            mPresenter.request();
        }else {
            //在没网的情况下读取GreenDao的数据展示。完成热销新品的展示。
            Toast.makeText(this, "列表暂时没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_show)
    public void show(){
        RetroiftUtil.getInstance().create(IRequest.class).result().enqueue(new Callback<User.ResultsBean>() {
            @Override
            public void onResponse(Call<User.ResultsBean> call, Response<User.ResultsBean> response) {
                User.ResultsBean resultsBean = response.body();
                ResultInfo mlss = resultsBean.mlss;
                List<Users> commodityList = mlss.commodityList;

                text_show.setText((CharSequence) commodityList);
            }

            @Override
            public void onFailure(Call<User.ResultsBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewSuccess(String json) {
        Log.i(TAG,json);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        if (user.status.equals("0000")){
            rxxpAdapter.addAll(user.result.rxxp.commodityList);
            rxxpAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewError(String msg) {

    }
}
