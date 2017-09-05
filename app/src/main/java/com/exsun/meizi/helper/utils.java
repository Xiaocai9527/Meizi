package com.exsun.meizi.helper;

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
}
