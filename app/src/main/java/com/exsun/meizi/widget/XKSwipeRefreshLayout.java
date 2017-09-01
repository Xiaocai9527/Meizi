package com.exsun.meizi.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by xiaokun on 2017/8/31.
 */

public class XKSwipeRefreshLayout extends SwipeRefreshLayout
{

    private float startY;
    private float startX;
    // 记录viewPager是否拖拽的标记
    private boolean mIsVpDragger;
    private final int mTouchSlop;
    private final View childAt;

    public XKSwipeRefreshLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        childAt = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                // 类似一个滑动锁，滑动的过程中不让拦截事件触发
                if (mIsVpDragger)
                {
                    return false;
                }

                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果X轴位移大于Y轴位移，那么将事件交给子view RecyclerView处理。
                if (distanceX > mTouchSlop && 3 * distanceX > distanceY)
                {
                    mIsVpDragger = true;
                    return false;
                } else
                {
                }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                mIsVpDragger = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public View childView;

    public void setChildView(View view)
    {
        this.childView = view;
    }

    public void addChidView(View view)
    {
        removeAllViews();
        addView(view);
    }
}
