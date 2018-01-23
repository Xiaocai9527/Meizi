package com.exsun.meizi.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共的adapter，模仿系统adapter
 *
 * @author xiaokun
 * @date 2017/10/17
 */

public abstract class CommenAdapter<T>
{

    public Context mContext;
    /**
     * 数据源
     */
    public List<T> mDatas;
    /**
     * 父控件ViewGroup
     */
    public ViewGroup mListView;

    /**
     * 父控件View
     */
    public View mListView2;

    public CommenAdapter(Context context)
    {
        mContext = context;
        mDatas = new ArrayList<>();
    }

    public CommenAdapter(Context context, List<T> datas)
    {
        mContext = context;
        mDatas = datas;
    }

    /**
     * 绑定视图
     *
     * @param listView view
     */
    public void bindListView(ViewGroup listView)
    {
        if (listView == null)
        {
            throw new IllegalArgumentException("CommentListView is null");
        }
        this.mListView = listView;
    }

    /**
     * 绑定视图
     *
     * @param listView view
     */
    public void bindListView(View listView)
    {
        if (listView == null)
        {
            throw new IllegalArgumentException("CommentListView is null");
        }
        this.mListView2 = listView;
    }

    public void setDatas(List<T> datas)
    {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public List<T> getDatas()
    {
        return mDatas;
    }

    public int getCount()
    {
        return mDatas == null ? 0 : mDatas.size();
    }

    public T getItem(int position)
    {
        if (mDatas == null)
        {
            return null;
        }
        if (mDatas.size() < position)
        {
            return mDatas.get(position);
        } else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * 刷新数据源
     */
    public abstract void notifyDataSetChanged();

//    /**
//     * 刷新视图
//     */
//    public void notifyDataSetChanged()
//    {
//        if (mListView == null)
//        {
//            throw new NullPointerException("mListView is null, please bindView first...");
//        }
//        //这里可以改进,一刀切可以不是一个好办法
//        mListView.removeAllViews();
//        if (mDatas == null || mDatas.size() == 0)
//        {
//            return;
//        }
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        int size = mDatas.size();
//        for (int i = 0; i < size; i++)
//        {
//            View view = getView(i);
//            //把子view添加进去,addView内部对child是否为空做了处理
//            mListView.addView(view, i, params);
//        }
//    }
//
//    /**
//     * 获得指定position的子View
//     *
//     * @param position 位置
//     */
//    public abstract View getView(int position);

}
