/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.exsun.meizi.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * toasts工具类
 */
public class Toasts
{

    public static Context sContext;


    private Toasts()
    {
    }


    public static void register(Context context)
    {
        sContext = context.getApplicationContext();
    }


    private static void check()
    {
        if (sContext == null)
        {
            throw new NullPointerException(
                    "Must initial call ToastUtils.register(Context context) in your " +
                            "<? " +
                            "extends Application class>");
        }
    }


    public static void showShort(int resId)
    {
        check();
        Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showShort(String message)
    {
        check();
        Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(int resId)
    {
        check();
        Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
    }


    public static void showLong(String message)
    {
        check();
        Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
    }

    private static Toast mToast;

    public static void showSingleShort(String message)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, message, Toast.LENGTH_SHORT);
        } else
        {
            mToast.setText(message);
        }
        mToast.show();
    }

    public static void showSingleShort(int resId)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, resId, Toast.LENGTH_SHORT);
        } else
        {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showSingleLong(String message)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, message, Toast.LENGTH_LONG);
        } else
        {
            mToast.setText(message);
        }
        mToast.show();
    }

    public static void showSingleLong(int resId)
    {
        check();
        if (mToast == null)
        {
            mToast = Toast.makeText(sContext, resId, Toast.LENGTH_LONG);
        } else
        {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showLongX2(String message)
    {
        showLong(message);
        showLong(message);
    }


    public static void showLongX2(int resId)
    {
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(int resId)
    {
        showLong(resId);
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(String message)
    {
        showLong(message);
        showLong(message);
        showLong(message);
    }
}
