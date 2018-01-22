package com.exsun.meizi.ui.douyu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exsun.meizi.entity.douyu.SlidersEntity;
import com.exsun.meizi.tool.ImageLoaderUtils;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class BannerPagerAdapter extends PagerAdapter
{
    private Context mContext;
    private List<SlidersEntity.DataBean> mDatas;

    public BannerPagerAdapter(Context context, List<SlidersEntity.DataBean> dataBeen)
    {
        this.mContext = context;
        this.mDatas = dataBeen;
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView imageView = new ImageView(mContext);
        ImageLoaderUtils.display(mContext, imageView, mDatas.get(position).getTv_pic_url());
        container.addView(imageView);
        return container;
    }
}
