package com.example.asus.er.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.er.R;
import com.example.asus.er.api.ApiStores;
import com.example.asus.er.api.AppClient;
import com.example.asus.er.ui.adapter.Fragment1RecycleAda;
import com.example.asus.er.ui.adapter.MyViewpager;
import com.example.asus.er.ui.model.F1DataBean;
import com.example.asus.er.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * autour: 孟向阳
 * date: 2017/1/13 11:57
 * update: 2017/1/13
 */
public class RibaoFragment extends Fragment {
    private MyViewpager viewpager;
    private ViewPager vp;
    private  int[] intData = {R.mipmap.ic_launcher,R.mipmap.ic_placeholder,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private ArrayList<ImageView> images;
    private Handler handler;
    private View view;
    private RecyclerView recyclerView;
    private List<F1DataBean.StoriesBean> dataBea;
    List<String> imgstr =new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ribao,container,false);
        imges();
        intView();
        loadDataRetrofit();
        //handler更新消息
        handler = new Handler();
        handler.postDelayed(new TimerRunnable(),3000);
        return view;
    }

    private void intView() {
        //设置适配
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        viewpager = new MyViewpager(images,getActivity());
        vp.setAdapter(viewpager);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler1);
        MyLinearLayoutManager mm = new MyLinearLayoutManager(getActivity());
        mm.setOrientation(MyLinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mm);
        recyclerView.setNestedScrollingEnabled(false);
    }
    //异步请求
    private void loadDataRetrofit() {
        ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
        Call<F1DataBean> dataBeanCall = apiStores.loadDataF1();
        dataBeanCall.enqueue(new Callback<F1DataBean>() {
            @Override
            public void onResponse(Call<F1DataBean> call, Response<F1DataBean> response) {
                dataBea = response.body().getStories();
                for(int i=0;i<dataBea.size();i++){
                    imgstr.addAll(dataBea.get(i).getImages());
                }
                Fragment1RecycleAda fragment1RecycleAda = new Fragment1RecycleAda(imgstr,getActivity(),dataBea);
                recyclerView.setAdapter(fragment1RecycleAda);
              /*  dataBeen = response.body().getData();
                Fragment2RecycleAda fragment2RecycleAda = new Fragment2RecycleAda(dataBeen,getActivity());
                recycle.setAdapter(fragment2RecycleAda);*/
                Log.e("===========","data="+response.body().getStories().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<F1DataBean> call, Throwable t) {
                Log.e("===========","请求失败！！");
            }
        });
    }


    private void imges() {
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i =0; i < intData.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(intData[i]);
            images.add(imageView);
        }
    }

    //创建一个线程
    class TimerRunnable implements Runnable{

        @Override
        public void run() {
            int curItem = vp.getCurrentItem();
            vp.setCurrentItem(curItem+1);
            if (handler!=null){
                handler.postDelayed(this,3000);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
