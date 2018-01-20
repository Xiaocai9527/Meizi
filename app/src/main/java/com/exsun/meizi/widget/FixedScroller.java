package com.exsun.meizi.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/05
 *     描述   : 更改scroller内部的默认duration  250ms ==>500ms
 *     版本   : 1.0
 * </pre>
 */

public class FixedScroller extends Scroller
{
    private int mDuration = 500;

    public FixedScroller(Context context)
    {
        super(context);
    }

    public FixedScroller(Context context, Interpolator interpolator)
    {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration)
    {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy)
    {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
