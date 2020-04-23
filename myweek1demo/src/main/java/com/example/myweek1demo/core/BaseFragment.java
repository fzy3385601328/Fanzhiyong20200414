package com.example.myweek1demo.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public BasePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayoutResId(), null);
        mPresenter = initPresenter();
        initView();
        getData();
        return view;
    }

    protected abstract BasePresenter initPresenter();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void getData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.destory();
            mPresenter=null;
        }
    }
}
