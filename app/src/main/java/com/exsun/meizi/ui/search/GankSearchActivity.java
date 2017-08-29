package com.exsun.meizi.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.SearchEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.widget.OffsetDecoration;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.utils.DimenUtils;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/8/12.
 */

public class GankSearchActivity extends BaseActivity<GankSearchPresenter, GankSearchModel>
        implements GankSearchContract.View
{
    @Bind(R.id.search_edit)
    EditText searchEdit;
    @Bind(R.id.search_toobar)
    Toolbar searchToobar;
    @Bind(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;
    @Bind(R.id.search_swipe)
    SwipeRefreshLayout searchSwipe;

    private int page = 1;
    private String trim;
    private String type;
    private int count = 20;

    public SearchAdapter adapter;
    private HeaderAndFooterWrapper wrapper;
    public List<SearchEntity.ResultsBean> datas;
    public static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private ImageView img;
    private boolean isClearData = true;
    private int IMAGE_HEIGHT = 200;//DP

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_gank_search;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView()
    {
        searchToobar.setContentInsetStartWithNavigation(0);
        searchToobar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));
        searchToobar.inflateMenu(R.menu.menu_search);
        searchToobar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        searchToobar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.android_search:
                        type = "Android";
                        trim = searchEdit.getText().toString().trim();
                        break;
                    case R.id.ios_search:
                        type = "IOS";
                        trim = searchEdit.getText().toString().trim();
                        break;
                    case R.id.front_search:
                        type = "前端";
                        trim = searchEdit.getText().toString().trim();
                        break;
                }
                if (!TextUtils.isEmpty(searchEdit.getText().toString().trim()))
                {
                    searchSwipe.setRefreshing(true);
                    mPresenter.getSearchData(trim, type, count, page);
                } else
                {
                    toastUtils.showSingleToast(R.string.please_input_something);
                }
                return false;
            }
        });
    }


    @Override
    public void doBusiness(Context context)
    {
        searchSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                isClearData = true;
                page = 1;
                mPresenter.getSearchData(trim, type, count, page);
            }
        });

        final int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        searchRecyclerView.addItemDecoration(new OffsetDecoration(spacing));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.addOnScrollListener(getOnButtomListener(layoutManager));

        datas = new ArrayList<>();
        adapter = new SearchAdapter(this, R.layout.item_other, datas);
        wrapper = new HeaderAndFooterWrapper(adapter);
        View headView = View.inflate(this, R.layout.head_view_img, null);
        img = (ImageView) headView.findViewById(R.id.head_img);
        wrapper.addHeaderView(img);
        searchRecyclerView.setAdapter(wrapper);
    }

    @Override
    public void getSearchSuccess(List<SearchEntity.ResultsBean> resultsBeanList)
    {
        String url = MzApplication.mPref.get(Constant.MY_LIKE_URL, "");
        ImageLoaderUtils.displaySize(this, img, url, DimenUtils.getScreenWidth()
                , (int) DimenUtils.dpToPx(IMAGE_HEIGHT));
        searchSwipe.setRefreshing(false);
        if (isClearData)
        {
            datas.clear();
        }
        datas.addAll(resultsBeanList);
        wrapper.notifyDataSetChanged();
        isClearData = false;
    }

    @Override
    public void getSearchFailed(Throwable throwable)
    {

    }

    @Override
    public void setStatusBar()
    {
        //        super.setStatusBar();
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
                if (!searchSwipe.isRefreshing() && isBottom)
                {
                    if (!mIsFirstTimeTouchBottom)
                    {
                        page++;
                        searchSwipe.setRefreshing(true);
                        mPresenter.getSearchData(trim, type, count, page);
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }
}
