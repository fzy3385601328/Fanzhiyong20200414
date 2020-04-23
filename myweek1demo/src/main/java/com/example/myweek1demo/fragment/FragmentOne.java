package com.example.myweek1demo.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myweek1demo.R;
import com.example.myweek1demo.core.BaseFragment;
import com.example.myweek1demo.core.BasePresenter;
import com.example.myweek1demo.util.UriTofilePath;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentOne extends BaseFragment {
    @BindView(R.id.view_image)
    ImageView viewImage;
    @BindView(R.id.btn_sao)
    Button btn_sao;
    private Bitmap mBitmap;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_one;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZXingLibrary.initDisplayOpinion(getActivity());
    }

    @Override
    protected void initView() {
        ButterKnife.bind(getActivity());
    }

    @Override
    protected void getData() {

    }

    @OnClick(R.id.view_image)
    public void image(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 200);
    }

    @OnClick(R.id.btn_sao)
    public void btnsao(){
        String url = "http://mobile.bwstudent.com/movieApi/movie/v2/findHotMovieList?page=1&count=10";
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        mBitmap = CodeUtils.createImage(url, 400, 400, null);
        viewImage.setImageBitmap(mBitmap);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (data != null) {
                Uri uri = data.getData();
                /*ContentResolver cr = getContentResolver();*/
                try {
                    String path = UriTofilePath.getFilePathByUri(getActivity(), uri);

                    CodeUtils.analyzeBitmap(path, new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                            /*EventBus.getDefault().postSticky(result);*/
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
