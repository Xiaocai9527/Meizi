package com.exsun.meizi.ui.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.helper.DoubleClickExitHelper;
import com.exsun.meizi.ui.fragment.GankFragment;
import com.exsun.meizi.ui.fragment.LikeFragment;
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
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.home_fl)
    FrameLayout homeFl;
    //    @Bind(R.id.toolbar)
//    Toolbar toolBar;
//    @Bind(R.id.tab_layout_home)
//    TabLayout tabLayoutHome;
//    @Bind(R.id.app_bar_layout)
//    AppBarLayout appBarLayout;
    private GankFragment gankFragment;
    private LikeFragment likeFragment;
    private DoubleClickExitHelper doubleClickExitHelper;

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

    private Fragment currentFragment;

    @Override
    public void initView()
    {
        initDrawLayout();
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

//        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
//        toolBar.setContentInsetStartWithNavigation(0);
//        toolBar.setNavigationOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

//        toolBar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));
//        toolBar.inflateMenu(R.menu.menu_main);
//
//        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
//        {
//            @Override
//            public boolean onMenuItemClick(MenuItem item)
//            {
//                switch (item.getItemId())
//                {
//                    case R.id.clear_cache:
//                        MzApplication.cache.clear();
//                        MzApplication.mPref.clear();
//                        Toasts.showSingleShort(R.string.clear_success);
//                        break;
//                    case R.id.action_change_skin:
//
//                        break;
//                    case R.id.search:
//
//                        break;
//                    default:
//
//                        break;
//                }
//                return false;
//            }
//        });
    }

    private void initFragment(String showFragment, boolean isFirst)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        gankFragment = GankFragment.getInstance();
        likeFragment = LikeFragment.getInstance();
        Fragment mFragment = null;
        switch (showFragment)
        {
            case "gank":
                mFragment = gankFragment;
//                toolBar.setTitle(R.string.follow_your_heart);
                break;
            case "like":
                mFragment = likeFragment;
//                toolBar.setTitle(R.string.my_like);
                break;
            default:

                break;
        }

        if (isFirst)
        {
            ft.add(R.id.home_fl, gankFragment).add(R.id.home_fl, likeFragment)
                    .hide(likeFragment).show(gankFragment).commit();
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
                        initFragment("like", false);
                        break;
                    case R.id.nav_meizi:
                        initFragment("gank", false);
                        break;
                    case R.id.nav_douyu:

                        break;
                    default:

                        break;
                }
            }
        }, 200);

        return true;
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

    @Override
    public void setStatusBar()
    {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
//        if (keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            if (gankFragment.getEditState() == View.VISIBLE)
//            {
//                gankFragment.back2Title();
//                return true;
//            }
//        }
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
