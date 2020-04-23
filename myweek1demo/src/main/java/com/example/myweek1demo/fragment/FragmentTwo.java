package com.example.myweek1demo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweek1demo.R;
import com.example.myweek1demo.adapter.MovieAdapter;
import com.example.myweek1demo.bean.MovieBean;
import com.example.myweek1demo.core.BaseFragment;
import com.example.myweek1demo.core.BasePresenter;
import com.example.myweek1demo.presenter.MoviePresenter;
import com.example.myweek1demo.util.IContract;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentTwo extends BaseFragment implements IContract.IView {
    private static final String TAG = "FragmentTwo";
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private MovieAdapter adapter;

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }*/

    @Override
    protected BasePresenter initPresenter() {
        return new MoviePresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this,getActivity());
    }


    /*@Subscribe(sticky = true)*/
    @Override
    public void getData() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        adapter = new MovieAdapter();
        recycler_view.setAdapter(adapter);

        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("count","5");
        mPresenter.request(map);
    }

    @Override
    public void onViewSuccess(String json) {
        Log.i(TAG,json);
        Gson gson = new Gson();
        MovieBean movieBean = gson.fromJson(json, MovieBean.class);

        if (movieBean.getStatus().equals("0000")){
            adapter.addAll(movieBean.getResult());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewError(String msg) {

    }

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//
    }*/
}
