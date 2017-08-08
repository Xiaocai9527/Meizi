package com.exsun.meizi.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/2.
 */

public class HomeVpAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public HomeVpAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTitles.get(position);
    }

    @Override
    public void finishUpdate(ViewGroup container)
    {
        try
        {
            super.finishUpdate(container);
        } catch (NullPointerException nullPointerException)
        {
            System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
        }
    }
}
