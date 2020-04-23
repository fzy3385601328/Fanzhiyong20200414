package com.example.myfan20200420.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfan20200420.R;
import com.example.myfan20200420.adapter.ResultAdapter;
import com.example.myfan20200420.bean.ResultListBean;
import com.example.myfan20200420.core.BaseActivity;
import com.example.myfan20200420.presenter.ResultPresenter;
import com.example.myfan20200420.presenter.RxxpPresenter;
import com.example.myfan20200420.util.IContract;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements IContract.IView {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ResultAdapter adapter;

    @Override
    protected RxxpPresenter initPresenter() {
        return new RxxpPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView = findViewById(R.id.rv_list);

        //设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //设置适配器
        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);

        mPresenter.request();
    }

    @Override
    public void onViewSuccess(String json) {
        Log.i(TAG,json);

        Gson gson = new Gson();
        ResultListBean resultListBean = gson.fromJson(json, ResultListBean.class);

        if (resultListBean.getStatus().equals("0000")){
            adapter.addAll(resultListBean.getResult());
            adapter.notifyDataSetChanged();

        }else {
            Toast.makeText(this, "无法显示列表", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewError(String msg) {

    }
}
