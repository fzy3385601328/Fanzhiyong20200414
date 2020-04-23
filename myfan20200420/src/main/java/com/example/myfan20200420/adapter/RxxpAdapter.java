package com.example.myfan20200420.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfan20200420.R;
import com.example.myfan20200420.bean.Users;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxxpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Users> list = new ArrayList<>();

    public void addAll(List<Users> commodityList){
        if (list!=null){
            list.addAll(commodityList);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rxxp_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Users users = list.get(position);
        Glide.with(((ViewHolder)holder).rxxp_image.getContext())
                .load(users.masterPic)
                .into(((ViewHolder)holder).rxxp_image);

        ((ViewHolder)holder).rxxp_name.setText(users.commodityName);

        ((ViewHolder)holder).rxxp_price.setText(String.valueOf(users.price));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rxxp_image)
        ImageView rxxp_image;
        @BindView(R.id.rxxp_name)
        TextView rxxp_name;
        @BindView(R.id.rxxp_price)
        TextView rxxp_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
