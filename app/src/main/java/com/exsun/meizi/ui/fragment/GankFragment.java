package com.exsun.meizi.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.ui.fragment.meizi.MeiziModel;
import com.exsun.meizi.ui.fragment.meizi.MeiziPresenter;
import com.exsun.meizi.widge.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaokun on 2017/8/2.
 */

public abstract class GankFragment extends BaseFragment<MeiziPresenter, MeiziModel>
{

    public static final String COLUMN_RV = "column_rv";
    public int column;
    public boolean isClearData = true;
    public int page = 1;
    public int count = 10;
    public String query1 = "";
    public String query2 = "";
    public boolean mIsFirstTimeTouchBottom = true;
    public static final int PRELOAD_SIZE = 6;

    @Bind(R.id.home_rv)
    protected
    RecyclerView homeRv;
    @Bind(R.id.home_sr)
    protected
    SwipeRefreshLayout homeSr;

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(Bundle bundle)
    {
        if (bundle == null)
        {
            return;
        }
        column = bundle.getInt(COLUMN_RV);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        initQuery();
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        homeRv.addItemDecoration(new OffsetDecoration(spacing));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(column,
                StaggeredGridLayoutManager.VERTICAL);
        homeRv.setLayoutManager(layoutManager);
        homeRv.addOnScrollListener(getOnButtomListener(layoutManager));
        homeSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                isClearData = true;
                page = 1;
                getData(query1, query2, count, page);
            }
        });
    }

    public abstract void initQuery();

    public abstract void getData(String query1, String query2, int count, int page);

    private RecyclerView.OnScrollListener getOnButtomListener(final StaggeredGridLayoutManager layoutManager)
    {
        return new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions = layoutManager.findLastCompletelyVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                boolean isBottom = positions[layoutManager.getSpanCount() - 1] >= (10 - PRELOAD_SIZE);
                if (!homeSr.isRefreshing() && isBottom)
                {
                    if (!mIsFirstTimeTouchBottom)
                    {
                        page++;
                        homeSr.setRefreshing(true);
                        getData(Constant.WELFARE, Constant.VIDEO, count, page);
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
