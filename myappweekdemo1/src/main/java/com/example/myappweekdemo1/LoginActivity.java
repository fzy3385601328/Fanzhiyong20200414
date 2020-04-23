package com.example.myappweekdemo1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myappweekdemo1.util.UriTofilePath;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static final int REQUEST_IMAGE = 100;
    private Bitmap image;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.btn_shengcheng)
    Button btn_shengcheng;
    @BindView(R.id.erwei_image)
    ImageView erweima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ZXingLibrary.initDisplayOpinion(this);

        //注册
        ButterKnife.bind(this);

        //上传图片

    }

    @OnClick(R.id.erwei_image)
    public void erweima(){
        String textContent = editText.getText().toString();
        if (TextUtils.isEmpty(textContent)) {
            Toast.makeText(LoginActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        editText.setText("");
        image = CodeUtils.createImage(textContent, 400, 400, null);
        erweima.setImageBitmap(image);
    }

    @OnClick(R.id.btn_search)
    public void search(){
        //checkSelfPermission方法就是检测是否有权限
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            //拥有权限
        }else {
            //没有权限，申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},200);
        }

        Intent intent = new Intent(LoginActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @OnClick(R.id.btn_shengcheng)
    public void shengcheng(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(LoginActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    String path = UriTofilePath.getFilePathByUri(getBaseContext(), uri);

                    CodeUtils.analyzeBitmap(path, new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(LoginActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(LoginActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
