package com.exsun.meizi.ui.douyu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.RoomsEntity;
import com.exsun.meizi.entity.douyu.RoomsWithSlidersEntity;
import com.exsun.meizi.entity.douyu.SlidersEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.ui.douyu.adapter.ChannelAdapter;
import com.exsun.meizi.ui.douyu.contract.ChannelContract;
import com.exsun.meizi.ui.douyu.model.ChannelModel;
import com.exsun.meizi.ui.douyu.presenter.ChannelPresenter;
import com.exsun.meizi.widget.OffsetDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.yuyh.library.Base.BaseFragment;
import com.yuyh.library.utils.DimenUtils;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class ChannelFragment extends BaseFragment<ChannelPresenter, ChannelModel> implements ChannelContract.View
{
    public static final String CATE_ID = "cate_id";
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private String cateId;
    private HeaderAndFooterWrapper wrapper;

    private int mLimit = 20;
    private int mOffset = 0;
    private View headView;
    private ViewPager banner;
    private ChannelAdapter channelAdapter;

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_douyu;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData(Bundle bundle)
    {
        if (bundle == null)
        {
            return;
        }
        cateId = bundle.getString(CATE_ID);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {

    }

    @Override
    public void doBusiness(Context context)
    {
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        if (cateId.equals("0"))
        {
            mPresenter.getRoomsWithSliders(cateId, mLimit, mOffset);
        } else
        {
            mPresenter.getRooms(cateId, mLimit, mOffset);
        }
    }

    @Override
    public void getRoomsSuccess(List<RoomsEntity.DataBean> roomEntities)
    {
        channelAdapter = new ChannelAdapter(mActivity, R.layout.item_room, roomEntities);
        if (recyclerView != null)
        {
            recyclerView.setAdapter(channelAdapter);
        }
    }

    @Override
    public void getRoomsFailed(Throwable throwable)
    {

    }

    @Override
    public void getMixSuccess(RoomsWithSlidersEntity roomsWithSlidersEntity)
    {
        List<SlidersEntity.DataBean> sliderData = roomsWithSlidersEntity.getSliderData();
        List<String> imgs = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        channelAdapter = new ChannelAdapter(mActivity, R.layout.item_room, roomsWithSlidersEntity.getRoomData());
        wrapper = new HeaderAndFooterWrapper(channelAdapter);
        Banner banner = new Banner(mActivity);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                DimenUtils.getScreenWidth(), (int) DimenUtils.dpToPx(200));
        banner.setLayoutParams(params);
        for (SlidersEntity.DataBean dataBean : sliderData)
        {
            imgs.add(dataBean.getTv_pic_url());
            titles.add(dataBean.getTitle());
        }
        banner.setImages(imgs);
        banner.setBannerTitles(titles);
        banner.setImageLoader(new ImageLoader()
        {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView)
            {
                ImageLoaderUtils.display(context, imageView, (String) path);
            }
        });
        //设置圆形指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
        wrapper.addHeaderView(banner);
        recyclerView.setAdapter(wrapper);
    }

    @Override
    public void getMixFailed(Throwable throwable)
    {

    }
}
