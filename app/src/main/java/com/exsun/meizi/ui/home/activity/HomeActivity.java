package com.exsun.meizi.ui.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.helper.DoubleClickExitHelper;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.ui.douyu.activity.DyMainActivity;
import com.exsun.meizi.ui.home.fragment.gank.GankFragment;
import com.exsun.meizi.ui.home.fragment.guolin.GuolinFragment;
import com.exsun.meizi.ui.home.fragment.like.LikeFragment;
import com.yuyh.library.AppUtils;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import cn.bmob.v3.BmobUser;

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
    private GuolinFragment guolinFragment;
    private DoubleClickExitHelper doubleClickExitHelper;
    private TextView nickName;
    private View headView;

    @Override
    public void initData(Bundle bundle)
    {
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
        initNavHeadView();
        initDrawLayout();
        EventBus.getDefault().register(this);
        if (gankFragment == null)
        {
            gankFragment = GankFragment.getInstance();
        }
        showFragment(gankFragment);
        doubleClickExitHelper = new DoubleClickExitHelper(this);
    }

    private void initNavHeadView()
    {
        View headerView = navView.getHeaderView(0);
        headView = headerView.findViewById(R.id.nav_header);
        ImageView headImg = (ImageView) headerView.findViewById(R.id.imageView_nav_header);
        nickName = (TextView) headerView.findViewById(R.id.nick_name);
        ImageLoaderUtils.displayRound(this, headImg, R.mipmap.ic_launcher);
        final boolean isLogin = MzApplication.mPref.get(Constant.IS_LOGIN, false);
        String nick = MzApplication.mPref.get(Constant.APP_NICKNAME, "");
        String location = MzApplication.mPref.get(Constant.APP_LOCATION, "");
        if (isLogin)
        {
            nickName.setText(nick + "-" + location);
        } else
        {
            nickName.setText("点击登录");
        }
        headView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean b = MzApplication.mPref.get(Constant.IS_LOGIN, false);
                if (b)
                {
                    new AlertDialog.Builder(mContext)
                            .setMessage("退出账号")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    //清空记录,退出登录
                                    MzApplication.mPref.clear();
                                    nickName.setText("点击登录");
                                    BmobUser.logOut();   //清除缓存用户对象
                                    BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.dismiss();
                                }
                            }).create().show();
                } else
                {
                    //登录或注册
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);

                    AppUtils.runOnUIDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }, 500);
                }
            }
        });
    }

    private void initDrawLayout()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();//添加导航开关
        navView.setNavigationItemSelectedListener(this);
    }

    private void showFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currentFragment == null)
        {
            ft.add(R.id.home_fl, fragment).show(fragment).commit();
        } else
        {
            if (!fragment.isAdded())
            {
                ft.add(R.id.home_fl, fragment);
            }
            //给fragment切换增加淡入淡出的动画
            ft.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
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
                            likeFragment = LikeFragment.getInstance();
                        }
                        showFragment(likeFragment);
//                        initFragment("like", false);
                        break;
                    case R.id.guolin_bolg:
                        if (guolinFragment == null)
                        {
                            guolinFragment = GuolinFragment.getInstance();
                        }
                        showFragment(guolinFragment);
                        break;
                    case R.id.nav_meizi:
                        if (gankFragment == null)
                        {
                            gankFragment = GankFragment.getInstance();
                        }
                        showFragment(gankFragment);
//                        initFragment("gank", false);
                        break;
                    case R.id.nav_douyu:
                        startActivity(new Intent(HomeActivity.this, DyMainActivity.class));
                        break;
                    case R.id.night_day_mode:
                        boolean b = MzApplication.mPref.get(Constant.DAY_NIGHT_STYLE, false);
                        b = !b;
                        setDayNightMode(b);
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
            MzApplication.mPref.put(Constant.DAY_NIGHT_STYLE, flag);
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            MzApplication.mPref.put(Constant.DAY_NIGHT_STYLE, flag);
        }
        finish();
//        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        startActivity(new Intent(this, HomeActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        recreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
//        outState.putParcelableArrayList();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MyUser myUser)
    {
        nickName.setText(myUser.getNickName() + "-" + myUser.getLocation());
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
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
            {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            return doubleClickExitHelper.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy()
    {
        gankFragment = null;
        likeFragment = null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
