package com.exsun.meizi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.ui.fragment.home.HomeFragment;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class HomeActivity extends BaseActivity
{
    @Bind(R.id.toolbar)
    Toolbar toolBar;
    @Bind(R.id.home_container)
    FrameLayout homeContainer;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_home;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        setSupportActionBar(toolBar);
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    //
                }
                return false;
            }
        });

        attachCategoryGridFragment();
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    private void attachCategoryGridFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.home_container);
        if (!(fragment instanceof HomeFragment))
        {
            fragment = HomeFragment.getInstance(null);
        }
        fm.beginTransaction().replace(R.id.home_container, fragment).commit();
    }

    @Override
    public void setStatusBar()
    {
        super.setStatusBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
