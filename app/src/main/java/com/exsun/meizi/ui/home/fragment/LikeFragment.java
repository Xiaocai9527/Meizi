package com.exsun.meizi.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.MyLikeEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.helper.Toasts;
import com.exsun.meizi.network.Api;
import com.exsun.meizi.network.ApiService;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.exsun.meizi.widget.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/8.
 */

public class LikeFragment extends BaseFragment
{
    @Bind(R.id.like_rv)
    RecyclerView likeRv;

    private static LikeFragment mLikeFragment = null;
    @Bind(R.id.like_refresh)
    SwipeRefreshLayout likeRefresh;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private CommonAdapter<MyLikeEntity> adapter;

    public static LikeFragment getInstance()
    {
        synchronized (LikeFragment.class)
        {
            if (mLikeFragment == null)
            {
                mLikeFragment = new LikeFragment();
            }
        }
        return mLikeFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_like;
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
        likeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refresh();
            }
        });
        toolbar.setTitle("我中意的");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));
        toolbar.inflateMenu(R.menu.menu_like);
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
                    case R.id.clear_cache:
                        MzApplication.cache.clear();
                        MzApplication.mPref.clear();
                        Toasts.showSingleShort(R.string.clear_success);
                        break;
                    case R.id.action_change_skin:

                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }

    private List<MyLikeEntity> myLikeEntities;

    @Override
    public void doBusiness(final Context context)
    {
        likeRefresh.setRefreshing(true);
        myLikeEntities = (List<MyLikeEntity>) MzApplication.cache.getAsObject(Constant.MY_LIKE_DATA);
        if (myLikeEntities == null)
        {
            myLikeEntities = new ArrayList<>();
        }
        likeRefresh.setRefreshing(false);
        likeRv.setLayoutManager(new LinearLayoutManager(mActivity));
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        likeRv.addItemDecoration(new OffsetDecoration(spacing));
        setAdapter(myLikeEntities);
    }

    /**
     * 刷新
     */
    private void refresh()
    {
        likeRefresh.setRefreshing(true);
        myLikeEntities = (List<MyLikeEntity>) MzApplication.cache.getAsObject(Constant.MY_LIKE_DATA);
        if (myLikeEntities == null)
        {
            myLikeEntities = new ArrayList<>();
        }
        likeRefresh.setRefreshing(false);
        setAdapter(myLikeEntities);
//        likeRv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();data数据源变化，无法使用notifyDataChange
    }

    /**
     * 设置adpater
     *
     * @param myLikeEntities 数据源
     */
    private void setAdapter(List<MyLikeEntity> myLikeEntities)
    {
        adapter = new CommonAdapter<MyLikeEntity>(mActivity, R.layout.item_my_like, myLikeEntities)
        {
            @Override
            protected void convert(final ViewHolder holder, final MyLikeEntity myLikeEntity, int position)
            {
                ApiService apiService = Api.getDefault(0);
                apiService.getRadomMz("福利", 1).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RadomMzEntity>()
                        {
                            @Override
                            public void onSubscribe(Disposable d)
                            {

                            }

                            @Override
                            public void onNext(RadomMzEntity value)
                            {
//                                String imgUrl = MzApplication.mPref.get(Constant.MY_LIKE_URL, "");
                                String imgUrl = value.getResults().get(0).getUrl();
                                ImageView imgView = holder.getView(R.id.img_like);
                                ImageLoaderUtils.display(mActivity, imgView, imgUrl);
                                value.getResults().get(0).getUrl();
                            }

                            @Override
                            public void onError(Throwable e)
                            {

                            }

                            @Override
                            public void onComplete()
                            {

                            }
                        });

                TextView tvDesc = holder.getView(R.id.my_like_desc);
                tvDesc.setText(myLikeEntity.getDesc());

                TextView tvAuthor = holder.getView(R.id.author_like);
                tvAuthor.setText("作者：" + myLikeEntity.getAuthor());

                holder.setOnClickListener(R.id.like_cv, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(mContext, BaseWebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(BaseWebActivity.WEB_URL, myLikeEntity.getUrl());
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
            }
        };
        likeRv.setAdapter(adapter);
    }

    public void setNight()
    {
        if (toolbar == null)
        {
            return;
        }
        toolbar.setBackgroundColor(Color.parseColor("#3F3F3F"));
        likeRv.setBackgroundColor(Color.parseColor("#3F3F3F"));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
