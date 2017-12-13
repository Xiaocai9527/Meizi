package com.exsun.meizi.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.exsun.meizi.widget.OffsetDecoration;

import java.lang.reflect.Field;

/**
 * Created by xiaokun on 2017/9/5.
 */

public class utils
{
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

}
