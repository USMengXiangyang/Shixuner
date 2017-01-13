package com.example.asus.er.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.er.R;
import com.example.asus.er.api.ApiStores;
import com.example.asus.er.api.AppClient;
import com.example.asus.er.ui.adapter.Fragment2RecycleAda;
import com.example.asus.er.ui.model.F2DataBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * autour: 孟向阳
 * date: 2017/1/6 13:55
 * update: 2017/1/6
 */
public class ZhuanLanFragment extends Fragment {

    private View view;
    private RecyclerView recycle;
    private List<F2DataBean.DataBean> dataBeen;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuanlan,container,false);
        //获得控件
        intView();
        loadDataRetrofit();
        return view;
    }

    private void intView() {
        recycle = (RecyclerView) view.findViewById(R.id.recycler);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        recycle.setLayoutManager(manager);
    }

    //异步请求
    private void loadDataRetrofit() {
        ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
        Call<F2DataBean> dataBeanCall = apiStores.loadDataF2();
        dataBeanCall.enqueue(new Callback<F2DataBean>() {
            @Override
            public void onResponse(Call<F2DataBean> call, Response<F2DataBean> response) {
                dataBeen = response.body().getData();
                Fragment2RecycleAda fragment2RecycleAda = new Fragment2RecycleAda(dataBeen,getActivity());
                recycle.setAdapter(fragment2RecycleAda);
//                Log.e("===========","data="+response.body().getData().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<F2DataBean> call, Throwable t) {
                Log.e("===========","请求失败！！");
            }
        });
    }


}
