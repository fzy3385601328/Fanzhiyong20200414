package com.example.greendao1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greendao1.bean.User;
import com.example.greendao1.dao.DaoMaster;
import com.example.greendao1.dao.DaoSession;
import com.example.greendao1.dao.UserDao;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_tell)
    TextView text_tell;
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
    }


   @OnClick(R.id.btn_insert)
    public void insert(){
        userDao.insertOrReplace(new User(150,"朱金贵","男","15130080625",20));
        Toast.makeText(this, "成功展示数据", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_query)
    public void query(){
        List<User> list = userDao.loadAll();

        StringBuilder builder = new StringBuilder();

        for (User user: list) {
            builder.append(user.id+""+user.name+""+user.sex+""+user.phone+""+user.age);
        }
        text_tell.setText(builder.toString());
    }


}

