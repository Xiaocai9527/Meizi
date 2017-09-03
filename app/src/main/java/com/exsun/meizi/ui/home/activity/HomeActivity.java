package com.exsun.meizi.ui.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.helper.DoubleClickExitHelper;
import com.exsun.meizi.ui.douyu.activity.DyMainActivity;
import com.exsun.meizi.ui.home.fragment.GankFragment;
import com.exsun.meizi.ui.home.fragment.LikeFragment;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private static final int REQUECT_CODE_SDCARD = 0;
    private static final int REQUEST_CODE_READ_PHONE_STATE = 1;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.home_fl)
    FrameLayout homeFl;
    private GankFragment gankFragment;
    private LikeFragment likeFragment;
    private DoubleClickExitHelper doubleClickExitHelper;
//    private boolean nightFlag = false;//进来默认白天模式

    @Override
    public void initData(Bundle bundle)
    {
//        if (MzApplication.mPref.get(Constant.DAY_NIGHT_STYLE, true))
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        } else
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
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

    private Fragment currentFragment;

    @Override
    public void initView()
    {
        initDrawLayout();
        if (gankFragment == null)
        {
            gankFragment = new GankFragment();

        }
        if (likeFragment == null)
        {
            likeFragment = new LikeFragment();

        }
        initFragment("gank", true);
        doubleClickExitHelper = new DoubleClickExitHelper(this);
    }

    private void initDrawLayout()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();//添加导航开关
        navView.setNavigationItemSelectedListener(this);
    }

    private void initFragment(String showFragment, boolean isFirst)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        gankFragment = GankFragment.getInstance();//单例模式会在recreat失效，因为onCreate不会重新调用
//        likeFragment = LikeFragment.getInstance();
        Fragment mFragment = null;
        switch (showFragment)
        {
            case "gank":
                mFragment = gankFragment;
                break;
            case "like":
                mFragment = likeFragment;
                break;
            default:

                break;
        }

        if (isFirst)
        {
            if (gankFragment.isAdded())
            {
                ft.remove(gankFragment);
            }
            if (likeFragment.isAdded())
            {
                ft.remove(likeFragment);
            }
            ft.add(R.id.home_fl, gankFragment);
            ft.add(R.id.home_fl, likeFragment);
            ft.hide(likeFragment).show(gankFragment).commit();
        } else
        {
            ft.hide(currentFragment).show(mFragment).commit();
        }
        currentFragment = mFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item)
    {
        drawerLayout.closeDrawer(GravityCompat.START);
        drawerLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                switch (item.getItemId())
                {
                    case R.id.my_like:
                        if (likeFragment == null)
                        {
                            likeFragment = new LikeFragment();
                        }
                        initFragment("like", false);
                        break;
                    case R.id.nav_meizi:
                        if (gankFragment == null)
                        {
                            gankFragment = new GankFragment();
                        }
                        initFragment("gank", false);
                        break;
                    case R.id.nav_douyu:
//                        startActivity(DyMainActivity.class);
                        startActivity(new Intent(HomeActivity.this, DyMainActivity.class));
                        break;
                    case R.id.night_day_mode:
                        boolean b = MzApplication.mPref.get(Constant.DAY_NIGHT_STYLE, false);
                        MzApplication.mPref.put(Constant.DAY_NIGHT_STYLE, !b);
//                        nightFlag = !nightFlag;//点击后将flag设置为非flag
                        setDayNightMode(b);
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
////                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                        StatusBarUtil.setColorNoTranslucent(HomeActivity.this, Color.parseColor("#3F3F3F"));
//                        navView.findViewById(R.id.nav_header).setBackgroundResource(R.drawable.night_bg);
//                        gankFragment.setNight();
//                        likeFragment.setNight();
//                        recreate();
                        break;
                    default:

                        break;
                }
            }
        }, 200);

        return true;
    }

    public void setDayNightMode(boolean flag)
    {
        if (flag)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            MzApplication.mPref.put(Constant.DAY_NIGHT_STYLE, false);
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            MzApplication.mPref.put(Constant.DAY_NIGHT_STYLE, true);
        }
//        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        startActivity(new Intent(this, HomeActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        recreate();
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
//        outState.putParcelableArrayList();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initTheme()
    {

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

    @PermissionGrant(REQUEST_CODE_READ_PHONE_STATE)
    public void setRequestCodeReadPhoneStateSuccess()
    {

    }

    @PermissionDenied(REQUEST_CODE_READ_PHONE_STATE)
    public void setRequestCodeReadPhoneStateFailed()
    {

    }

    @Override
    public void setStatusBar()
    {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            return doubleClickExitHelper.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy()
    {
        gankFragment = null;
        likeFragment = null;
        super.onDestroy();
    }
}
