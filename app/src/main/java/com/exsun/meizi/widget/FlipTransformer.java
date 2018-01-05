package com.exsun.meizi.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/05
 *     描述   : viewpager翻页动画--模仿翻书效果
 *     版本   : 1.0
 * </pre>
 */

public class FlipTransformer implements ViewPager.PageTransformer
{
    @Override
    public void transformPage(View view, float position)
    {
        if (position < 0)
        {
            //将x坐标完全定位在屏幕的左边缘
            view.setTranslationX(view.getMeasuredWidth() * -position);
            view.setPivotX(0);
            view.setPivotY(view.getMeasuredHeight() * 0.5f);
            view.setRotationY(20f * position);
            view.setAlpha(position + 1);
        } else if (position < 1)
        {
            view.setTranslationX(view.getMeasuredWidth() * -position);
            view.setPivotX(0);
            view.setPivotY(view.getMeasuredHeight() * 0.5f);
            view.setRotationY(20f * position);
            view.setAlpha(1 - position);
        }
        /**
         * 解决新页面不能点击的问题
         */
        if (position < -1 || position == -1)
        {
            //屏幕左侧完全不可见
            view.setVisibility(View.INVISIBLE);
        } else if (position < 1)
        {
            //(-1,1)区间,屏幕范围能够看得到,可见
            view.setVisibility(View.VISIBLE);
        } else
        {
            //屏幕右侧完全不可见
            view.setVisibility(View.INVISIBLE);
        }
    }
}
