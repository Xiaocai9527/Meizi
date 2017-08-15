package com.exsun.meizi.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.ui.home.activity.HomeActivity;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity
{
    @Nullable
    @Bind(R.id.activity_splash)
    LinearLayout activitySplash;

    private int mDelayTime = 1500;

    private Runnable mGotoMainRunnable = new Runnable()
    {
        public void run()
        {
            Intent localIntent = new Intent(SplashActivity.this, HomeActivity.class);
            SplashActivity.this.startActivity(localIntent);
            SplashActivity.this.finish();
        }
    };

    protected void onPause()
    {
        super.onPause();
        getWindow().getDecorView().removeCallbacks(this.mGotoMainRunnable);
    }

    protected void onResume()
    {
        super.onResume();
        getWindow().getDecorView().postDelayed(this.mGotoMainRunnable, this.mDelayTime);
    }

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_splash;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        activitySplash.setBackgroundResource(R.mipmap.splash);
    }

    @Override
    public void doBusiness(Context context)
    {

    }
}
