package com.exsun.meizi.ui.home.fragment.like;

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

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.MyLikeEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.helper.Toasts;
import com.exsun.meizi.network.Api;
import com.exsun.meizi.network.ApiService;
import com.exsun.meizi.ui.picture.PictureActivity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.exsun.meizi.widget.OffsetDecoration;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.Base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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

    @Bind(R.id.like_refresh)
    SwipeRefreshLayout likeRefresh;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.empty_tv)
    TextView emptyTv;

    private CommonAdapter<MyLikeEntity> adapter;

    public static LikeFragment getInstance()
    {
        LikeFragment mLikeFragment = new LikeFragment();
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
        toolbar.setTitle("我的收藏");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setTitleTextColor(Color.parseColor("#efefef"));
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
                        List<MyLikeEntity> myLikeEntities = new ArrayList<>();
                        MzApplication.cache.put(Constant.MY_LIKE_DATA, (Serializable) myLikeEntities);
                        if (myLikeEntities != null && !myLikeEntities.isEmpty())
                        {
                            int size = myLikeEntities.size();
                            for (int i = 0; i < size; i++)
                            {
                                MyLikeEntity myLikeEntity = myLikeEntities.get(i);
                                String url = myLikeEntity.getUrl();
                                MzApplication.mPref.put(url, false);
                            }
                        }
                        refresh();
                        Toasts.showSingleShort(R.string.clear_success);
                        break;
                    case R.id.action_change_skin:
                        Toasts.showSingleShort("请在侧边栏操作,谢谢!");
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
        EventBus.getDefault().register(this);
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
        if (myLikeEntities.isEmpty())
        {
            likeRefresh.setVisibility(View.GONE);
            emptyTv.setVisibility(View.VISIBLE);
        } else
        {
            likeRefresh.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            setAdapter(myLikeEntities);
        }
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
        if (myLikeEntities.isEmpty())
        {
            likeRefresh.setVisibility(View.GONE);
            emptyTv.setVisibility(View.VISIBLE);
        } else
        {
            likeRefresh.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            setAdapter(myLikeEntities);
        }
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
                                final RadomMzEntity.ResultsBean resultsBean = value.getResults().get(0);
                                final String imgUrl = resultsBean.getUrl();
                                final ImageView imgView = holder.getView(R.id.img_like);
                                ImageLoaderUtils.display(mActivity, imgView, imgUrl);

                                imgView.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        PictureActivity.jumpToPictureActivity((BaseActivity) mContext, imgUrl, resultsBean.getDesc(), imgView);
                                    }
                                });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(List<MyLikeEntity> likeEntities)
    {
        if (likeEntities == null || likeEntities.isEmpty())
        {
            likeRefresh.setVisibility(View.GONE);
            emptyTv.setVisibility(View.VISIBLE);
        } else
        {
            likeRefresh.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
        }
        setAdapter(likeEntities);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
