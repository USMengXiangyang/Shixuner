package com.example.asus.er.api;

import com.example.asus.er.ui.model.F1DataBean;
import com.example.asus.er.ui.model.F2DataBean;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * autour: 孟向阳
 * date: 2017/1/12 11:14
 * update: 2017/1/12
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://news-at.zhihu.com/";

    //加载专栏
    @GET("api/4/sections")
    Call<F2DataBean> loadDataF2();
//    http://news-at.zhihu.com/api/4/sections
    //加载日报
    @GET("api/4/news/latest")
    Call<F1DataBean> loadDataF1();
    //加载微信
    @GET("wxnew/?key=89bc539c8d6424551960925d21c0eab3&num=1&page=1")
    Observable<F2DataBean> loadDataByRetrofitRxjava();
}
