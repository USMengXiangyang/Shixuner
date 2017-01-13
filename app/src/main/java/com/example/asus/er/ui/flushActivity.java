package com.example.asus.er.ui;

import android.Manifest;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.asus.er.R;
import com.example.mylibrary.base.BaseActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class flushActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_flush;
    }
    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .compose(RxPermissions.getInstance(this).ensureEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(permission -> {      //lambda表达式
                    if (permission.granted) {
                        startActivity(MainActivity.class);
                        finish();
                    }
                });
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

   /* Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(flushActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
