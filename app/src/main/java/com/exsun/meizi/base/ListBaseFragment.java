package com.exsun.meizi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.exsun.meizi.R;
import com.exsun.meizi.tool.LoadMoreDelegate;
import com.exsun.meizi.tool.SwipeRefreshDelegate;
import com.exsun.meizi.widget.OffsetDecoration;
import com.yuyh.library.Base.BaseFragment;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.Bind;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/22
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public abstract class ListBaseFragment extends BaseFragment
        implements SwipeRefreshDelegate.OnSwipeRefreshListener, LoadMoreDelegate.LoadMoreSubject
{
    private static final String TAG = ListBaseFragment.class.getSimpleName();

    @Bind(android.R.id.list)
    RecyclerView recyclerView;

    private SwipeRefreshDelegate refreshDelegate;
    private LoadMoreDelegate loadMoreDelegate;

    private AtomicInteger loadingCount;
    private boolean isEnd;

    protected MultiTypeAdapter adapter;
    protected Items items;

    @Override
    protected int getLayoutId()
    {
        return R.layout.swipe_recycler_layout;
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
        items = new Items();
        adapter = new MultiTypeAdapter(items);
        refreshDelegate = new SwipeRefreshDelegate(this);
        loadMoreDelegate = new LoadMoreDelegate(this);
        //初始int值为0
        loadingCount = new AtomicInteger(0);
    }

    @Override
    public void doBusiness(Context context)
    {
        int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadMoreDelegate.attach(recyclerView);
        refreshDelegate.attach(contentView);
        loadData(true);
    }

    protected abstract void loadData(boolean clear);

    protected boolean onInterceptLoadMore()
    {
        return false;
    }

    protected void setRefresh(boolean refresh)
    {
        refreshDelegate.setRefresh(refresh);
    }

    @Override
    public void onSwipeRefresh()
    {
        loadData(true);
    }

    @Override
    public final void onLoadMore()
    {
        if (!isEnd())
        {
            Log.d(TAG, "[onLoadMore]" + "isEnd == false");
            if (!onInterceptLoadMore())
            {
                loadData(false);
            }
        }
    }

    protected boolean isShowingRefresh()
    {
        return refreshDelegate.isShowingRefresh();
    }

    public void setEnd(boolean end)
    {
        isEnd = end;
    }

    public boolean isEnd()
    {
        return isEnd;
    }

    protected void setSwipeToRefreshEnabled(boolean enable)
    {
        refreshDelegate.setEnabled(enable);
    }

    public void smoothScrollToPosition(int position)
    {
        recyclerView.smoothScrollToPosition(position);
    }

    @Override
    public boolean isLoading()
    {
        return loadingCount.get() > 0;
    }

    protected void notifyLoadingStarted()
    {
        //加载开始自增
        loadingCount.getAndIncrement();
    }

    protected void notifyLoadingFinished()
    {
        //加载结束自减
        loadingCount.decrementAndGet();
    }
}
