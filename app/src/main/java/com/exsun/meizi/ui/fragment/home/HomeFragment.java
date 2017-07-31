package com.exsun.meizi.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.HomeMixEntity;
import com.exsun.meizi.ui.adapter.HomeRvAdapter;
import com.exsun.meizi.widge.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class HomeFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View
{

    private static HomeFragment mHomeFragment = null;
    public static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private boolean isClearData = true;

    @Bind(R.id.home_rv)
    RecyclerView homeRv;
    @Bind(R.id.home_sr)
    SwipeRefreshLayout homeSr;

    private int page = 1;
    private int count = 10;
    private HomeRvAdapter adapter;
    private List<HomeMixEntity> datas;

    public static HomeFragment getInstance(Bundle bundle)
    {
        synchronized (HomeFragment.class)
        {
            if (mHomeFragment == null)
            {
                mHomeFragment = new HomeFragment();
                mHomeFragment.setArguments(bundle);
            }
        }
        return mHomeFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        homeRv.addItemDecoration(new OffsetDecoration(spacing));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
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
                getCategory(Constant.WELFARE, Constant.VIDEO, count, page);
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {
        mPresenter.getMixDataFormDB();
    }

    private void getCategory(String welfare, String category, int count, int page)
    {
        homeSr.setRefreshing(true);
        mPresenter.getMixData(welfare, category, count, page);
    }

    @Override
    public void getDataFromDBSuccess(List<HomeMixEntity> homeMixEntities)
    {
        datas = new ArrayList<HomeMixEntity>();
        datas.addAll(homeMixEntities);
        adapter = new HomeRvAdapter(mActivity, R.layout.item_category, datas);
        homeRv.setAdapter(adapter);
        getCategory(Constant.WELFARE, Constant.VIDEO, count, page);
    }

    @Override
    public void getMixSuccess(List<HomeMixEntity> homeMixEntities)
    {
        homeSr.setRefreshing(false);
        if (isClearData)
        {
            datas.clear();
        }
        datas.addAll(homeMixEntities);
        adapter.notifyDataSetChanged();
        isClearData = false;
    }

    @Override
    public void getCategoryFailed(Throwable e)
    {
        homeSr.setRefreshing(false);
    }

    private RecyclerView.OnScrollListener getOnButtomListener(final StaggeredGridLayoutManager layoutManager)
    {
        return new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions = layoutManager.findLastCompletelyVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                boolean isBottom = positions[layoutManager.getSpanCount() - 1] >= (adapter.getItemCount() - PRELOAD_SIZE);
                Log.e("HomeFragment", "onScrolled(HomeFragment.java:147)" + positions[layoutManager.getSpanCount() - 1]);
                Log.e("HomeFragment", "onScrolled(HomeFragment.java:149)" + adapter.getItemCount());
                if (!homeSr.isRefreshing() && isBottom)
                {
                    if (!mIsFirstTimeTouchBottom)
                    {
                        page++;
                        homeSr.setRefreshing(true);
                        getCategory(Constant.WELFARE, Constant.VIDEO, count, page);
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }
}
