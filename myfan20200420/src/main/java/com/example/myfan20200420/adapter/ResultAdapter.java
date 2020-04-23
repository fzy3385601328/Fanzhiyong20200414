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
import com.example.myfan20200420.bean.ResultBean;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ResultBean> list = new ArrayList<>();

    public void addAll(List<ResultBean> result){
        if (list!=null)
        list.addAll(result);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultBean resultBean = list.get(position);
        Glide.with(((ViewHolder)holder).itemImage.getContext())
                .load(resultBean.getImageUrl())
                .into(((ViewHolder)holder).itemImage);
        ((ViewHolder)holder).itemName.setText(resultBean.getTitle());
        ((ViewHolder)holder).itemPrice.setText(String.valueOf(resultBean.getRank()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemImage;
        private final TextView itemName,itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
