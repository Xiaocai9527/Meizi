package com.yuyh.library.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyh.library.Base.util.TUtil;
import com.yuyh.library.utils.toast.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by xiaokun on 2017/7/26.
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment
{
    private static final String TAG = "BaseFragment";
    public static final String STATE_SAVE_IS_HIDDEN = "state_save_is_hidden";
    public P mPresenter;
    public M mModel;
    /**
     * 当前Activity渲染的视图View
     */
    protected View contentView;
    protected BaseActivity mActivity;
    public ToastUtils toastUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden)
            {
                ft.hide(this);
            } else
            {
                ft.show(this);
            }
            ft.commit();
        }
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setRetainInstance(true);
        if (contentView == null)
            contentView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, contentView);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null)
        {
            mPresenter.mContext = this.getActivity();
        }
        initPresenter();
        Log.d(TAG, "onCreateView: ");
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initData(bundle);
        initView(savedInstanceState, contentView);
        Log.d(TAG, "onViewCreated: ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        toastUtils = new ToastUtils();
        doBusiness(mActivity);
        Log.d(TAG, "onActivityCreated: ");
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化presenter层
     */
    protected abstract void initPresenter();

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的bundle
     */
    public abstract void initData(Bundle bundle);

    /**
     * 初始化view
     */
    public abstract void initView(Bundle savedInstanceState, final View view);

    /**
     * 业务操作
     *
     * @param context 上下文
     */
    public abstract void doBusiness(Context context);

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls)
    {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode)
    {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode)
    {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle)
    {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ActivityCompat.startActivity(mActivity, intent, null);
    }

    @Override
    public void onDestroyView()
    {
        if (contentView != null)
        {
            ((ViewGroup) contentView.getParent()).removeView(contentView);
        }
        ButterKnife.unbind(this);
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (outState != null)
        {
            outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        }
    }
}
