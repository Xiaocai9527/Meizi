package com.exsun.meizi.ui.home.fragment.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.exsun.meizi.R;
import com.exsun.meizi.ui.home.adapter.HomeVpAdapter;
import com.exsun.meizi.ui.home.fragment.meizi.MeiziFragment;
import com.exsun.meizi.ui.home.fragment.other.OtherFragment;
import com.exsun.meizi.ui.search.GankSearchActivity;
import com.exsun.meizi.widget.FixedScroller;
import com.exsun.meizi.widget.FlipTransformer;
import com.yuyh.library.Base.BaseFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaokun on 2017/8/8.
 */
@Keep
public class GankFragment extends BaseFragment
{
    @Bind(R.id.view_pager_home)
    ViewPager viewPagerHome;

    @Bind(R.id.tab_layout_home)
    TabLayout tabLayoutHome;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static GankFragment getInstance()
    {
        GankFragment mGankFragment = new GankFragment();
        return mGankFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        initToolBar();
        initViewPager();
//        if (savedInstanceState == null)
//        {
//            initToolBar();
//            initViewPager();
//        }
    }

    private void initToolBar()
    {
        toolbar.setTitle(R.string.follow_your_heart);
        toolbar.setTitleTextColor(Color.parseColor("#efefef"));
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.inflateMenu(R.menu.menu_gank);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.search:
                        startActivity(new Intent(mActivity, GankSearchActivity.class));
                        //                        mActivity.startActivity(GankSearchActivity.class);
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {

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

        List<Fragment> fragments = new ArrayList<>();
        Bundle mzBundle = new Bundle();
        mzBundle.putInt(MeiziFragment.COLUMN_RV, 2);
        fragments.add(MeiziFragment.getInstance(mzBundle));

        Bundle aBundle = new Bundle();
        aBundle.putInt(OtherFragment.COLUMN_RV, 1);
        aBundle.putString(OtherFragment.ANDROID_CETOGARY, "Android");
        fragments.add(OtherFragment.getInstance(aBundle));

        Bundle iBundle = new Bundle();
        iBundle.putString(OtherFragment.IOS_CETOGARY, "iOS");
        fragments.add(OtherFragment.getInstance(iBundle));

        Bundle fBundle = new Bundle();
        fBundle.putString(OtherFragment.FRONT_CETOGARY, "前端");
        fragments.add(OtherFragment.getInstance(fBundle));

        changViewpagerTime();
        viewPagerHome.setOffscreenPageLimit(3);
        viewPagerHome.setPageTransformer(true, new FlipTransformer());
        HomeVpAdapter homeVpAdapter = new HomeVpAdapter(getChildFragmentManager(), fragments, titles);
        viewPagerHome.setAdapter(homeVpAdapter);
        tabLayoutHome.setupWithViewPager(viewPagerHome);
//        tabLayoutHome.setTabsFromPagerAdapter(homeVpAdapter);

    }

    /**
     * 改变viewpager自动播放的滑动时间为500ms 默认是250ms
     */
    private void changViewpagerTime()
    {
        try
        {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
            FixedScroller scroller = new FixedScroller(viewPagerHome.getContext(), sInterpolator);
            mScroller.set(viewPagerHome, scroller);
        } catch (NoSuchFieldException e)
        {
        } catch (IllegalArgumentException e)
        {
        } catch (IllegalAccessException e)
        {
        }
    }

    public void setNight()
    {
        if (toolbar == null || tabLayoutHome == null || viewPagerHome == null)
        {
            return;
        }
        toolbar.setBackgroundColor(Color.parseColor("#3F3F3F"));
        tabLayoutHome.setBackgroundColor(Color.parseColor("#3F3F3F"));
        viewPagerHome.setBackgroundResource(R.drawable.night_bg);
        //        viewPagerHome.setBackgroundColor(Color.parseColor("#3F3F3F"));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
