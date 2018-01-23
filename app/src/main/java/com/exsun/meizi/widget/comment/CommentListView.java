package com.exsun.meizi.widget.comment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.exsun.meizi.widget.CommenAdapter;


/**
 * Created by 肖坤 on 2017/10/17.
 * company: exsun
 * email: 838494268@qq.com
 */

public class CommentListView extends LinearLayout
{
    private CommenAdapter commenAdapter;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    interface OnItemLongClickListener
    {
        void onItemLongClick();
    }

    public CommentListView(Context context)
    {
        super(context);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(CommenAdapter adapter)
    {
        this.commenAdapter = adapter;
        commenAdapter.bindListView(this);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.onItemLongClickListener = listener;
    }

//    public void

}
