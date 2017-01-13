package com.example.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mylibrary.AppManager;
import com.example.mylibrary.utils.LogUtil;

public abstract class BaseActivity extends AppCompatActivity {

    private String TAG;
    private BaseActivity mContext;
    private boolean isOpen = false;
    private ImageView ivShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        this.setContentView(this.getLayoutId());
        mContext = this;
        this.initView(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    @Override
    public void setContentView(int layoutResID) {
        if (isOpen()) {
            super.setContentView(layoutResID);
        } else {
            super.setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
           /* view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            swipeBackLayout.addView(view);*/
        }
    }
    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
       /* swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);*/
        ivShadow = new ImageView(this);
//        ivShadow.setBackgroundColor(getResources().getColor(R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
//        container.addView(swipeBackLayout);
//        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public abstract int getLayoutId();
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 跳转页面,无extra简易型
     *
     * @param tarActivity 目标页面
     */
    public void startActivity(Class<? extends Activity> tarActivity, Bundle options) {
        Intent intent = new Intent(this, tarActivity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options);
        } else {
            startActivity(intent);
        }
    }
    public void startActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }

    public void showLog(String msg) {
        LogUtil.i(TAG, msg);// TODO: 16/10/12 Log需要自己从新搞一下
    }
}
