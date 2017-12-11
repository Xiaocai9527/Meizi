package com.exsun.meizi.ui.home.fragment.meizi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.HomeMixEntity;
import com.exsun.meizi.ui.home.adapter.MzRvAdapter;
import com.exsun.meizi.widget.OffsetDecoration;
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
    private boolean isClearData = false;
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
        return R.layout.fragment_meizi;
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
                getData(query1, query2, count, page, true);
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {
        datas = new ArrayList<>();
        adapter = new MzRvAdapter(context, R.layout.item_category, datas);
        homeRv.setAdapter(adapter);
        boolean b = MzApplication.mPref.get(Constant.IS_FIRST_OPEN_APP, true);
        if (b)
        {
            getData(Constant.WELFARE, Constant.VIDEO, count, page, true);
        } else
        {
            mPresenter.getMixDataFormDB();
        }
    }

    @Override
    public void getDataFromDBSuccess(List<HomeMixEntity> homeMixEntities)
    {
        datas.addAll(homeMixEntities);
        adapter.notifyDataSetChanged();
        //进去不自动刷新,需要手动下拉刷新,数据才会刷新
        //getData(Constant.WELFARE, Constant.VIDEO, count, page, false);
    }

    @Override
    public void getMixSuccess(List<HomeMixEntity> homeMixEntities)
    {
        if (homeSr != null)
        {
            homeSr.setRefreshing(false);
        }
        if (isClearData)
        {
            datas.clear();
        }
        datas.addAll(homeMixEntities);
        adapter.notifyDataSetChanged();
        isClearData = false;
        MzApplication.mPref.put(Constant.IS_FIRST_OPEN_APP, false);
    }

    /**
     * @param query1           福利
     * @param query2           休息视频
     * @param count            每页count
     * @param page             页码
     * @param isSaveToDataBase 是否加入缓存
     */
    public void getData(String query1, String query2, int count, int page, boolean isSaveToDataBase)
    {
        if (homeSr != null)
        {
            homeSr.setRefreshing(true);
        }
        mPresenter.getMixData(query1, query2, count, page, isSaveToDataBase);
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
                        getData(Constant.WELFARE, Constant.VIDEO, count, page, false);
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }
}
