package com.exsun.meizi.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xiaokun on 2017/8/3.
 */

public class BaseWebActivity extends BaseActivity
{
    public static final String WEB_URL = "web_url";
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    LinearLayout container;
    private String url;

    protected AgentWeb mAgentWeb;

    @Override
    public void initData(Bundle bundle)
    {
        if (bundle == null)
        {
            return;
        }
        url = bundle.getString(WEB_URL);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_base_web;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {
        mAgentWeb = AgentWeb.with(this)//传入Activity
                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()// 使用默认进度条
                .setIndicatorColor(R.color.colorPrimary)
//                .defaultProgressBarColor()// 使用默认进度条颜色
                .setReceivedTitleCallback(mCallback)//设置 Web 页面的 title 回调
//                .setWebChromeClient(mWebChromeClient)
//                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
//                .setWebLayout(new WebLayout(this))
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    public void setStatusBar()
    {
//        super.setStatusBar();
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback()
    {
        @Override
        public void onReceivedTitle(WebView view, String title)
        {
            if (toolbarTitle != null)
                toolbarTitle.setText(title);
        }
    };
}
