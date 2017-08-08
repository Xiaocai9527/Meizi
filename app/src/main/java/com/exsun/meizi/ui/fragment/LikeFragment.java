package com.exsun.meizi.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.MzApplication;
import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.MyLikeEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.ui.activity.BaseWebActivity;
import com.exsun.meizi.widge.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.setTitle("我中意的");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private List<MyLikeEntity> myLikeEntities;

    @Override
    public void doBusiness(final Context context)
    {
        refresh();
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
        likeRv.setLayoutManager(new LinearLayoutManager(mActivity));
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        likeRv.addItemDecoration(new OffsetDecoration(spacing));
        likeRv.setAdapter(new CommonAdapter<MyLikeEntity>(mActivity, R.layout.item_my_like, myLikeEntities)
        {
            @Override
            protected void convert(ViewHolder holder, final MyLikeEntity myLikeEntity, int position)
            {
                String imgUrl = MzApplication.mPref.get(Constant.MY_LIKE_URL, "");
                ImageView imgView = holder.getView(R.id.img_like);
                ImageLoaderUtils.display(mActivity, imgView, imgUrl);

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
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
