package com.exsun.meizi.ui.home.fragment.community;

import android.os.Bundle;
import android.view.View;

import com.exsun.meizi.base.ListBaseFragment;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.tool.Toasts;
import com.exsun.meizi.ui.multitype.CircleViewBinder;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.drakeet.multitype.Items;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/22
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class CircleFragment extends ListBaseFragment
{

    public CircleFragment()
    {

    }

    public static CircleFragment getInstance()
    {
        CircleFragment mCircleFragment = new CircleFragment();
        return mCircleFragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        super.initView(savedInstanceState, view);
        CircleViewBinder binder = new CircleViewBinder();
        binder.setRefreshListener(new CircleViewBinder.RefreshListener()
        {
            @Override
            public void refresh()
            {
                loadData(true);
            }
        });
        adapter.register(TalkMoodEntity.class, binder);
    }

    @Override
    protected void loadData(boolean clear)
    {
        setRefresh(true);
        loadDataFromRemote(clear);
    }

    public void loadDataFromRemote(final boolean clear)
    {
        notifyLoadingStarted();
        BmobQuery<TalkMoodEntity> query = new BmobQuery<>();
        query.setLimit(10);
        query.findObjects(new FindListener<TalkMoodEntity>()
        {
            @Override
            public void done(List<TalkMoodEntity> list, BmobException e)
            {
                if (e == null)
                {
                    setRefresh(false);
                    notifyLoadingFinished();

                    if (list.size() < 10)
                    {
                        setEnd(true);
                    }

                    Toasts.showSingleShort("加载成功：共" + list.size() + "条数据。");
                    Items tempItems = clear ? new Items() : new Items(items);
                    tempItems.addAll(list);
                    items = tempItems;
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                } else
                {
                    Toasts.showSingleShort("加载失败:" + e.getMessage());
                }
            }
        });
    }

//    @Override
//    public void onSwipeRefresh()
//    {
////        super.onSwipeRefresh();
//        loadDataFromRemote(true);
//    }

    @Override
    protected boolean onInterceptLoadMore()
    {
        if (!isLoading())
        {
            loadDataFromRemote(false);
        }
        return true;
    }
}
