package com.example.fanzhiyong20200414.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fanzhiyong20200414.R;
import com.example.fanzhiyong20200414.core.BaseActivity;
import com.example.fanzhiyong20200414.core.BasePresenter;

/**
 * WebView代码
 */
public class WebActivity extends BaseActivity {


    private WebView webView;
    private Button btnUpdate;
    private EditText editUpdate;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //找控件
        editUpdate = findViewById(R.id.edit_update);
        btnUpdate = findViewById(R.id.btn_update);
        webView = findViewById(R.id.Web_View);

        //设置webView路径的方法
        initUrl();

        //webView具体代码实现
        initWeb();
    }

    private void initUrl() {
        //设置WebView路径
        webView.loadUrl("file:///android_asset/shop.html");
    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
        WebSettings settings = webView.getSettings();//得到她的具体实现类
        settings.setJavaScriptEnabled(true);//设置是否与Js关联，这里默认为true;
        webView.addJavascriptInterface(this,"android");

        //这里分别调用web的setWebChromeClient和setWebViewClient方法
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        //点击修改按钮修改商品名称
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户的修改数据
                String text = editUpdate.getText().toString();
                webView.loadUrl("javascript:changeTitle('"+text+"')");
            }
        });
    }

    //这里调用buy的方法
    @JavascriptInterface
    public void buy(String id){
        Toast.makeText(this, "商品"+id, Toast.LENGTH_SHORT).show();
    }
}
