package com.exsun.meizi.ui.douyu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.exsun.meizi.entity.douyu.ChannelsEntity;
import com.exsun.meizi.ui.douyu.fragment.ChannelFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class ChannelPageAdapter extends FragmentStatePagerAdapter
{
    private List<ChannelsEntity.DataBean> channelEntityList = new ArrayList();

    public ChannelPageAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public void updateData(List<ChannelsEntity.DataBean> channelEntityList)
    {
        this.channelEntityList = channelEntityList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position)
    {
        ChannelFragment channelFragment = new ChannelFragment();
        Bundle bundle = new Bundle();
        if (position == 0)
        {
            bundle.putString(ChannelFragment.CATE_ID, "0");
        } else
        {
            bundle.putString(ChannelFragment.CATE_ID, channelEntityList.get(position - 1).getCate_id());
        }
        channelFragment.setArguments(bundle);
        return channelFragment;
    }

    @Override
    public int getCount()
    {
        return this.channelEntityList.size() + 1;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position == 0)
        {
            return "首页";
        }
        return channelEntityList.get(position - 1).getGame_name();
    }
}
