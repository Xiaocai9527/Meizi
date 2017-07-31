package com.yuyh.library.Base.util;

/**
 * Created by xiaokun on 2017/7/26.
 * 快速点击判断
 */

public class FastClickHelper
{
    /**
     * 上次点击时间
     */
    public static long lastClick = 0;

    public static boolean isFastClick()
    {
        boolean isFastClick;//是否允许点击
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500)
        {
            isFastClick = false;
        } else
        {
            isFastClick = true;
        }
        lastClick = now;
        return isFastClick;
    }
}
