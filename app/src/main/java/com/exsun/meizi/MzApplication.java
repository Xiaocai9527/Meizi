package com.exsun.meizi;

import android.app.Application;
import android.content.Context;

import com.exsun.meizi.helper.Toasts;
import com.github.moduth.blockcanary.BlockCanary;
import com.litesuits.orm.LiteOrm;
import com.squareup.leakcanary.LeakCanary;
import com.yuyh.library.AppUtils;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class MzApplication extends Application
{
    private static final String DB_NAME = "meizhi.db";
    public static LiteOrm sDb;
    private static MzApplication appApplication;

    @Override
    public void onCreate()
    {
        super.onCreate();
        appApplication = this;
        AppUtils.init(this);
        Toasts.register(this);
        sDb = LiteOrm.newSingleInstance(this, DB_NAME);
        if (BuildConfig.DEBUG)
        {
            sDb.setDebugged(true);
            BlockCanary.install(this, new AppContext()).start();
            LeakCanary.install(this);
        }
    }

    public static Context getAppContext()
    {
        return appApplication;
    }

}
