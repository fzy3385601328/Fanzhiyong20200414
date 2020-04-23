package com.example.myweek1demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myweek1demo.R;
import com.example.myweek1demo.bean.ResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<ResultBean> list = new ArrayList<>();

    public void addAll(List<ResultBean> result){
        list.addAll(result);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultBean resultBean = list.get(position);
        RequestOptions options = new RequestOptions()
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .fallback(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher);

        Glide.with(((ViewHolder)holder).item_image.getContext())
                .applyDefaultRequestOptions(options)
                .load(resultBean.getImageUrl())
                .into(((ViewHolder)holder).item_image);

        ((ViewHolder)holder).movieId.setText(String.valueOf(resultBean.getMovieId()));
        ((ViewHolder)holder).name.setText(resultBean.getName());
        ((ViewHolder)holder).starring.setText(resultBean.getDirector());
        ((ViewHolder)holder).score.setText(String.valueOf(resultBean.getScore()));
        ((ViewHolder)holder).starring.setText(resultBean.getStarring());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView item_image;
        @BindView(R.id.movieId)
        TextView movieId;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.director)
        TextView director;
        @BindView(R.id.score)
        TextView score;
        @BindView(R.id.starring)
        TextView starring;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
