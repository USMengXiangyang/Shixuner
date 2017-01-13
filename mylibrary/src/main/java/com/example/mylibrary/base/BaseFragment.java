package com.example.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mylibrary.utils.LogUtil;
import com.example.mylibrary.utils.ToastUtils;

/**
 * 作用：
 * 时间：2016/1124
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Activity mActivity;
    private OnBackToFirstListener _mBackToFirstListener;
    private String TAG;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), null);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //设置状态栏透明
//        setStatusBarColor();
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
//        if (this instanceof CoreBaseView) mPresenter.attachVM(this, mModel);
//        getBundle(getArguments());
//        initData();
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }
    /**
     * 处理回退事件
     * 如果是孩子fragment需要重写onBackPressedSupport(){_mBackToFirstListener.onBackToFirstFragment();return true;}
     *
     * @return
     */
   /* @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            _mBackToFirstListener.onBackToFirstFragment();
            _mActivity.finish();
        }
        return true;
    }*/
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public void showLog(String msg) {
        LogUtil.i(TAG, msg);// TODO: 16/10/12 Log需要自己从新搞一下
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
