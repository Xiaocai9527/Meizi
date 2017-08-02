package com.exsun.meizi.ui.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.exsun.meizi.R;
import com.exsun.meizi.ui.adapter.HomeVpAdapter;
import com.exsun.meizi.ui.fragment.meizi.AndroidFragment;
import com.exsun.meizi.ui.fragment.meizi.MeiziFragment;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class HomeActivity extends BaseActivity
{
    private static final int REQUECT_CODE_SDCARD = 0;
    @Bind(R.id.toolbar)
    Toolbar toolBar;
    @Bind(R.id.tab_layout_home)
    TabLayout tabLayoutHome;
    @Bind(R.id.view_pager_home)
    ViewPager viewPagerHome;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public void initData(Bundle bundle)
    {
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
        initToolBar();
        initNavigationView();
        initViewPager();
//        attachCategoryGridFragment();
    }

    private void initToolBar()
    {
        setSupportActionBar(toolBar);
    }

    private void initNavigationView()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();//添加导航开关

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                return false;
            }
        });
        navView.setItemIconTintList(null);
    }

    private void initViewPager()
    {
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.meizi));
        titles.add(getString(R.string.android));
        titles.add(getString(R.string.ios));
        titles.add(getString(R.string.front));

        tabLayoutHome.addTab(tabLayoutHome.newTab().setText(R.string.meizi));
        tabLayoutHome.addTab(tabLayoutHome.newTab().setText(R.string.android));
        tabLayoutHome.addTab(tabLayoutHome.newTab().setText(R.string.ios));
        tabLayoutHome.addTab(tabLayoutHome.newTab().setText(R.string.front));

        Bundle bundle = new Bundle();
        bundle.putInt(MeiziFragment.COLUMN_RV, 2);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MeiziFragment.getInstance(bundle));
        fragments.add(AndroidFragment.getInstance(bundle));
        fragments.add(MeiziFragment.getInstance(bundle));
        fragments.add(MeiziFragment.getInstance(bundle));

        viewPagerHome.setOffscreenPageLimit(3);
        HomeVpAdapter homeVpAdapter = new HomeVpAdapter(getSupportFragmentManager(), fragments, titles);
        viewPagerHome.setAdapter(homeVpAdapter);
        tabLayoutHome.setupWithViewPager(viewPagerHome);
        tabLayoutHome.setTabsFromPagerAdapter(homeVpAdapter);
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess()
    {

    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed()
    {

    }

    private void attachCategoryGridFragment()
    {

//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.home_container);
//        if (!(fragment instanceof MeiziFragment))
//        {
//            fragment = MeiziFragment.getInstance(null);
//        }
//        fm.beginTransaction().replace(R.id.home_container, fragment).commit();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:

                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
