package com.exsun.meizi.ui.douyu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.exsun.meizi.widget.XKSwipeRefreshLayout;
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
    @Bind(R.id.refresh_layout)
    XKSwipeRefreshLayout refreshLayout;
    private String cateId;
    private HeaderAndFooterWrapper wrapper;

    private int mLimit = 20;
    private int mOffset = 0;
    private View headView;
    private ViewPager banner;
    private ChannelAdapter channelAdapter;
    private ChannelAdapter channelWithSliderAdapter;

    private List<RoomsEntity.DataBean> mData;
    private List<RoomsEntity.DataBean> mDataWithSlider;
    private boolean isClearData = true;//首次进来或者触发刷新

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
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                isClearData = true;
                mOffset = 0;
                if (cateId.equals("0"))
                {
                    mPresenter.getRoomsWithSliders(cateId, mLimit, mOffset);
                } else
                {
                    mPresenter.getRooms(cateId, mLimit, mOffset);
                }
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {

        if (recyclerView != null)
        {
            recyclerView.setAdapter(channelAdapter);
        }
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(getOnButtomListener(manager));
        if (cateId.equals("0"))
        {
            mDataWithSlider = new ArrayList<>();
            channelWithSliderAdapter = new ChannelAdapter(context, R.layout.item_room, mDataWithSlider);
            recyclerView.setAdapter(channelWithSliderAdapter);
            mPresenter.getRoomsWithSliders(cateId, mLimit, mOffset);
        } else
        {
            mData = new ArrayList<>();
            channelAdapter = new ChannelAdapter(context, R.layout.item_room, mData);
            recyclerView.setAdapter(channelAdapter);
            mPresenter.getRooms(cateId, mLimit, mOffset);
        }
    }

    @Override
    public void getRoomsSuccess(List<RoomsEntity.DataBean> roomEntities)
    {
        refreshLayout.setRefreshing(false);
        if (isClearData)
        {
            mData.clear();
        }
        mData.addAll(roomEntities);
        if (channelAdapter != null)
        {
            channelAdapter.notifyDataSetChanged();
        }
        isClearData = false;
    }

    @Override
    public void getRoomsFailed(Throwable throwable)
    {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getMixSuccess(RoomsWithSlidersEntity roomsWithSlidersEntity)
    {
        refreshLayout.setRefreshing(false);
        if (isClearData)
        {
            mDataWithSlider.clear();
            mDataWithSlider.addAll(roomsWithSlidersEntity.getRoomData());
            List<SlidersEntity.DataBean> sliderData = roomsWithSlidersEntity.getSliderData();
            List<String> imgs = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            wrapper = new HeaderAndFooterWrapper(channelWithSliderAdapter);
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
            isClearData = false;
            return;
        }
        mDataWithSlider.addAll(roomsWithSlidersEntity.getRoomData());
        wrapper.notifyDataSetChanged();
        isClearData = false;
    }

    @Override
    public void getMixFailed(Throwable throwable)
    {
        refreshLayout.setRefreshing(false);
    }

    public static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;

    private RecyclerView.OnScrollListener getOnButtomListener(final GridLayoutManager layoutManager)
    {
        return new RecyclerView.OnScrollListener()
        {

            private boolean isBottom;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                int positions = layoutManager.findLastCompletelyVisibleItemPosition();
                if (cateId.equals("0"))
                {
                    isBottom = positions >= (channelWithSliderAdapter.getItemCount() - PRELOAD_SIZE);
                } else
                {
                    isBottom = positions >= (channelAdapter.getItemCount() - PRELOAD_SIZE);
                }

                if (!refreshLayout.isRefreshing() && isBottom)
                {
                    if (!mIsFirstTimeTouchBottom)
                    {
                        mOffset = mOffset + mLimit;
                        refreshLayout.setRefreshing(true);
                        if (cateId.equals("0"))
                        {
                            mPresenter.getRoomsWithSliders(cateId, mLimit, mOffset);
                        } else
                        {
                            mPresenter.getRooms(cateId, mLimit, mOffset);
                        }
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }
}
