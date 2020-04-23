package com.example.fanzhiyong20200414.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fanzhiyong20200414.R;
import com.example.fanzhiyong20200414.core.BaseActivity;
import com.example.fanzhiyong20200414.core.BasePresenter;
import com.example.fanzhiyong20200414.custom.FlowLayout;

public class SearchActivity extends BaseActivity {


    private EditText editSearch;
    private FlowLayout flowLayout;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        editSearch = findViewById(R.id.edit_search);
        flowLayout = findViewById(R.id.flow_layout);

        //点击事件
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的数据进行展示
                String text = editSearch.getText().toString();
                //添加到流失布局中
                flowLayout.addText(text);
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据
                flowLayout.removeAllViews();
            }
        });

        flowLayout.setFlowClickener(new FlowLayout.onFlowClickener() {
            @Override
            public void FlowClick(String text) {
                //跳转到主页面
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("text",text);
                //启动跳转
                startActivity(intent);
                finish();
            }
        });
    }
}
