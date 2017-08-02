package com.exsun.meizi.ui.fragment.meizi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.GankCategoryEntity;
import com.exsun.meizi.entity.HomeMixEntity;
import com.exsun.meizi.ui.adapter.MzRvAdapter;
import com.exsun.meizi.widge.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class MeiziFragment extends BaseFragment<MeiziPresenter, MeiziModel> implements MeiziContract.View
{
    public static final String COLUMN_RV = "meizi_column_rv";
    public static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private boolean isClearData = true;
    public int column;

    @Bind(R.id.home_rv)
    RecyclerView homeRv;
    @Bind(R.id.home_sr)
    SwipeRefreshLayout homeSr;

    private int page = 1;
    private int count = 10;
    private MzRvAdapter adapter;
    private List<HomeMixEntity> datas;
    private String query1 = "";
    private String query2 = "";

    public static MeiziFragment getInstance(Bundle bundle)
    {
        MeiziFragment mMeiziFragment = new MeiziFragment();
        mMeiziFragment.setArguments(bundle);
        return mMeiziFragment;
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
        if (bundle == null)
        {
            return;
        }
        column = bundle.getInt(MeiziFragment.COLUMN_RV);
    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        query1 = Constant.WELFARE;
        query2 = Constant.VIDEO;
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        homeRv.addItemDecoration(new OffsetDecoration(spacing));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
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

    @Override
    public void doBusiness(Context context)
    {
        mPresenter.getMixDataFormDB();
    }

    @Override
    public void getCategorySuccess(List<GankCategoryEntity.ResultsBean> resultsBeanList)
    {

    }

    @Override
    public void getDataFromDBSuccess(List<HomeMixEntity> homeMixEntities)
    {
        datas = new ArrayList<>();
        datas.addAll(homeMixEntities);
        adapter = new MzRvAdapter(mActivity, R.layout.item_category, datas);
        homeRv.setAdapter(adapter);
        getData(Constant.WELFARE, Constant.VIDEO, count, page);
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

    public void getData(String query1, String query2, int count, int page)
    {
        homeSr.setRefreshing(true);
        mPresenter.getMixData(query1, query2, count, page);
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
}
