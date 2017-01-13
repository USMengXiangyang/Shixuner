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
import com.example.asus.er.ui.model.F2DataBean;

import java.util.List;

/**
 * autour: 孟向阳
 * date: 2017/1/9 13:33
 * update: 2017/1/9
 */
public class Fragment2RecycleAda extends RecyclerView.Adapter<Fragment2RecycleAda.MyViewHolder> {
   List<F2DataBean.DataBean> dataBeen;
    Context context;

    public Fragment2RecycleAda(List<F2DataBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.context = context;
    }

    @Override
    public Fragment2RecycleAda.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.f2_recycleitem,parent,false));
    }

    @Override
    public void onBindViewHolder(Fragment2RecycleAda.MyViewHolder holder, int position) {
        holder.desc.setText(dataBeen.get(position).getDescription());
        holder.name.setText(dataBeen.get(position).getName());
        Glide.with(context)
                .load(dataBeen.get(position).getThumbnail())
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓冲全尺寸
                .centerCrop()//设置居中
                .placeholder(R.mipmap.icon_face_nomal)//设置占位图
                .error(R.mipmap.ic_placeholder)//加载错误图
                .into(holder.girl_pic);
    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView girl_pic;
        private final TextView desc;
        private final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            girl_pic = (ImageView) itemView.findViewById(R.id.girl_pic);
            desc = (TextView) itemView.findViewById(R.id.desc);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
