package com.exsun.meizi.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Keep;

import com.exsun.meizi.BuildConfig;
import com.exsun.meizi.helper.Toasts;
import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.litesuits.orm.LiteOrm;
import com.squareup.leakcanary.LeakCanary;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.ACache;
import com.yuyh.library.utils.data.PrefsUtils;

import cn.bmob.v3.Bmob;
import me.drakeet.library.CrashWoodpecker;
import me.drakeet.library.PatchMode;

/**
 * Created by xiaokun on 2017/7/26.
 */
@Keep
public class MzApplication extends Application
{
    private static final String DB_NAME = "meizhi.db";
    public static LiteOrm sDb;
    private static MzApplication appApplication;
    public static PrefsUtils mPref;
    public static ACache cache;

    @Override
    public void onCreate()
    {
        super.onCreate();
        appApplication = this;
        AppUtils.init(this);
        Toasts.register(this);
        cache = ACache.get(this);
        mPref = new PrefsUtils(this, "app_data");
        sDb = LiteOrm.newSingleInstance(this, DB_NAME);
        if (BuildConfig.DEBUG)
        {
            sDb.setDebugged(true);
            BlockCanary.install(this, new AppContext()).start();
            LeakCanary.install(this);
            Stetho.initializeWithDefaults(this);
        }
        CrashWoodpecker.instance()
                .withKeys("widget", "me.drakeet")
                .setPatchMode(PatchMode.SHOW_LOG_PAGE)
//                .setPatchDialogUrlToOpen("https://drakeet.me")
                .setPassToOriginalDefaultHandler(true)
                .flyTo(this);
        Bmob.initialize(this, "0b35de938314b2aeafb67aeed1dc96a8");
    }

    public static Context getAppContext()
    {
        return appApplication;
    }

}
