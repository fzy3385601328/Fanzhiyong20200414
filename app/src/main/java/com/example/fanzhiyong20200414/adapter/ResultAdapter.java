package com.example.fanzhiyong20200414.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fanzhiyong20200414.R;
import com.example.fanzhiyong20200414.bean.ResultBean;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //创建集合
    List<ResultBean> list = new ArrayList<>();

    public void addAll(List<ResultBean> result){
        if (list!=null){
            list.addAll(result);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载条目布局
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ResultBean resultBean = list.get(position);
        //加载图片的代码
        RequestOptions options = new RequestOptions()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(45)))
                .fallback(R.mipmap.ic_launcher)
                .error(R.drawable.qq)
                .placeholder(R.drawable.wx);

        //glide加载图片
         Glide.with(((ViewHolder)holder).itemImage.getContext())
                 .applyDefaultRequestOptions(options)
                 .load(resultBean.getMasterPic())
                 .into(((ViewHolder)holder).itemImage);

        ((ViewHolder)holder).itemName.setText(resultBean.getCommodityName());
        ((ViewHolder)holder).itemPrice.setText(String.valueOf(resultBean.getPrice()));

        ((ViewHolder)holder).itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickener!=null){
                    itemClickener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }

    private OnItemClickener itemClickener;

    public void setItemClickener(OnItemClickener itemClickener) {
        this.itemClickener = itemClickener;
    }

    //设置点击事件
    public interface OnItemClickener{
        void onItemClick(int position);
    }
}
