package com.exsun.meizi.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.exsun.meizi.widget.OffsetDecoration;

import java.lang.reflect.Field;

/**
 * Created by xiaokun on 2017/9/5.
 */

public class utils
{
    /**
     * 设置textview的drawable位置
     */
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;

    public static String obj2json(Object obj)
    {
        Class c = obj.getClass();
        Field fields[] = c.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        try
        {
            for (int i = 0; i < fields.length; i++)
            {
                String name = fields[i].getName();
                fields[i].setAccessible(true);
                String value = fields[i].get(obj).toString();
                sb.append("'" + name + "':" + "'" + value + "',");
            }
        } catch (Exception e)
        {
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * 初始化rv 简易包装
     *
     * @param recyclerView
     * @param column       列
     * @param space        间隔
     * @param direction    方向  1：竖直  2:水平
     * @return
     */
    public static RecyclerView initRv(RecyclerView recyclerView, int column, int space, int direction)
    {
        recyclerView.addItemDecoration(new OffsetDecoration(space));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(column, direction);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    public static TextView setTextDrawable(TextView textView, Context mContext, int ranking_back_icon, int left, int pad)
    {

        Drawable drawable = ContextCompat.getDrawable(mContext, ranking_back_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (left == LEFT)
        {
            textView.setCompoundDrawables(drawable, null, null, null);//左上右下
        } else if (left == TOP)
        {
            textView.setCompoundDrawables(null, drawable, null, null);//左上右下
        } else if (left == RIGHT)
        {
            textView.setCompoundDrawables(null, null, drawable, null);//左上右下
        } else if (left == BOTTOM)
        {
            textView.setCompoundDrawables(null, null, null, drawable);//左上右下
        }
        textView.setCompoundDrawablePadding(pad);
        return textView;
    }

}
