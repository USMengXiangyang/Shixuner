package com.example.asus.er.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * autour: 孟向阳
 * date: 2017/1/12 11:36
 * update: 2017/1/12
 */

public abstract class RetrofitCallback<M> implements Callback<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();

    @Override
    public void onResponse(Call<M> call, Response<M> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFailure(response.code(), response.errorBody().toString());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }
}
