package com.example.asus.er.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * autour: 孟向阳
 * date: 2017/1/12 15:45
 * update: 2017/1/12
 */
public class MyViewpager extends PagerAdapter {
    private List<ImageView> intDatas;
    Context context;

    public MyViewpager(List<ImageView> intDatas, Context context) {
        this.intDatas = intDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(intDatas.get(position%intDatas.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            container.addView(intDatas.get(position%intDatas.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intDatas.get(position % intDatas.size());
    }
}
