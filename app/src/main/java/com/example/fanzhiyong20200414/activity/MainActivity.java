package com.example.fanzhiyong20200414.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fanzhiyong20200414.R;
import com.example.fanzhiyong20200414.adapter.ResultAdapter;
import com.example.fanzhiyong20200414.bean.ResultBean;
import com.example.fanzhiyong20200414.bean.ResultListBean;
import com.example.fanzhiyong20200414.core.BaseActivity;
import com.example.fanzhiyong20200414.core.BasePresenter;
import com.example.fanzhiyong20200414.presenter.ResultPresenter;
import com.example.fanzhiyong20200414.util.IContract;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements IContract.IView {


    private RecyclerView recyclerView;
    private EditText searchEdit;
    private Button searchBtn;
    private ResultAdapter adapter;
    private ResultListBean resultListBean;

    @Override
    protected BasePresenter initPresenter() {
        return new ResultPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
         //找控件
        searchEdit = findViewById(R.id.search_edit);
        searchBtn = findViewById(R.id.search_btn);
        recyclerView = findViewById(R.id.recycler_view);

        //设置RecyclerView布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //设置适配器
        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);

        String text = getIntent().getStringExtra("text");
        //使用Map集合存储数据
        Map<String,String> map=new HashMap<>();
        map.put("keyword",text);
        map.put("page","1");
        map.put("count","5");

        //发送请求
        mPresenter.request(map);

        //点击列表条目跳转到WebView界面
        adapter.setItemClickener(new ResultAdapter.OnItemClickener() {
            @Override
            public void onItemClick(int position) {
                //吐司数据并调整
                ResultBean resultBean = resultListBean.getResult().get(position);
                Toast.makeText(MainActivity.this, "吐司商品名称"+resultBean.getCommodityName(), Toast.LENGTH_SHORT).show();
                //而后跳转
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                startActivity(intent);
                //关闭本页面
                finish();
            }
        });
    }

    @Override
    public void onViewSuccess(String json) {
        //判断请求成功后
        Log.i("fzy",json);
        //Gson解析
        Gson gson = new Gson();
        resultListBean = gson.fromJson(json, ResultListBean.class);

        //使用Bean类的校验码进行判断
        if (resultListBean.getStatus().equals("0000")){
            adapter.addAll(resultListBean.getResult());
            //刷新适配器
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, resultListBean.getMessage()+""+ resultListBean.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewError(String msg) {
        //判断请求失败后
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
