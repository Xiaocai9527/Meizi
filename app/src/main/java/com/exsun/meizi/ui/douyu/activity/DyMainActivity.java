package com.exsun.meizi.ui.douyu.activity;

import android.content.Context;
import android.os.Bundle;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.ChannelEntity;
import com.exsun.meizi.ui.douyu.contract.DyMainContract;
import com.exsun.meizi.ui.douyu.model.DyMainModel;
import com.exsun.meizi.ui.douyu.presenter.DyMainPresenter;
import com.yuyh.library.Base.BaseActivity;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class DyMainActivity extends BaseActivity<DyMainPresenter,DyMainModel>implements DyMainContract.View
{
    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_dy_main;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView()
    {

    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @Override
    public void getChannelsSuccess(List<ChannelEntity> channelEntities)
    {

    }

    @Override
    public void getChannelsFailed(Throwable throwable)
    {

    }
}
