package com.example.asus.er.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus.er.R;
import com.example.asus.er.ui.model.F1DataBean;

import java.util.List;

/**
 * autour: 孟向阳
 * date: 2017/1/13 13:49
 * update: 2017/1/13
 */
public class Fragment1RecycleAda extends RecyclerView.Adapter<Fragment1RecycleAda.MyViewHolder> {

    private List<String> data;
    private Context context;
    private List<F1DataBean.StoriesBean> dataTitle;

    public Fragment1RecycleAda(List<String> data, Context context, List<F1DataBean.StoriesBean> dataTitle) {
        this.data = data;
        this.context = context;
        this.dataTitle = dataTitle;
    }

    @Override
    public Fragment1RecycleAda.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.f2_cyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Fragment1RecycleAda.MyViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓冲全尺寸
                .centerCrop()//设置居中
                .placeholder(R.mipmap.icon_face_nomal)//设置占位图
                .error(R.mipmap.ic_placeholder)//加载错误图
                .into(holder.f1_img);
        holder.f1_title.setText(dataTitle.get(position%dataTitle.size()).getTitle());

//        Log.e("-------","imgUrl="+data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView f1_img;
        private final TextView f1_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            f1_img = (ImageView) itemView.findViewById(R.id.f1_img);
            f1_title = (TextView) itemView.findViewById(R.id.f1_title);
        }
    }
}
