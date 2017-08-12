package com.yuyh.library.Base;

import android.content.Context;

import com.yuyh.library.Base.rx.RxManager;

/**
 * Created by xiaokun on 2017/7/26.
 */

public abstract class BasePresenter<M, V>
{
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();
    public Context mContext;

    public void attachVM(V v, M m)
    {
        this.mModel = m;
        this.mView = v;
    }

    public void setVM(V v, M m)
    {
        this.mModel = m;
        this.mView = v;
    }

    public void detachVM()
    {
        mRxManager.clear();
        mView = null;
        mModel = null;
    }
}
