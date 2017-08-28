package com.exsun.meizi.ui.douyu.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.ChannelsEntity;
import com.exsun.meizi.ui.douyu.adapter.ChannelPageAdapter;
import com.exsun.meizi.ui.douyu.contract.DyMainContract;
import com.exsun.meizi.ui.douyu.model.DyMainModel;
import com.exsun.meizi.ui.douyu.presenter.DyMainPresenter;
import com.yuyh.library.Base.BaseActivity;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class DyMainActivity extends BaseActivity<DyMainPresenter, DyMainModel> implements DyMainContract.View
{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout_home)
    TabLayout tabLayoutHome;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private ChannelPageAdapter pageAdapter;

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
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView()
    {
        tabLayoutHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        pageAdapter = new ChannelPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabLayoutHome.setupWithViewPager(viewPager);
    }

    @Override
    public void doBusiness(Context context)
    {
        mPresenter.getChannelsData();
    }

    @Override
    public void getChannelsSuccess(List<ChannelsEntity.DataBean> channelEntities)
    {
        pageAdapter.updateData(channelEntities);
    }

    @Override
    public void getChannelsFailed(Throwable throwable)
    {

    }
}
