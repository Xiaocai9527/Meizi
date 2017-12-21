package com.exsun.meizi.ui.web;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.MyLikeEntity;
import com.exsun.meizi.helper.Shares;
import com.exsun.meizi.helper.Toasts;
import com.exsun.meizi.ui.picture.PictureActivity;
import com.just.library.AgentWeb;
import com.just.library.AgentWebUtils;
import com.just.library.ChromeClientCallbackManager;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.Base.util.RxTransUtil;
import com.yuyh.library.utils.log.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by xiaokun on 2017/8/3.
 */

public class BaseWebActivity extends BaseActivity
{
    public static final String WEB_URL = "web_url";
    public static final String WEB_DESC = "web_desc";
    public static final String WEB_AUTHOR = "web_author";
    public static final String IS_PATTERN = "is_pattern";

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    LinearLayout container;
    private String url;
    private String descTitle;
    private String author;
    private boolean isPat;
    protected AgentWeb mAgentWeb;
    private List<MyLikeEntity> myLikeEntities;
    private WebView webView;

    public static void jumpToBaseWebActivity(Activity activity, String url, String desc, String author, View shareView, boolean isPattern)
    {
        Intent intent = new Intent(activity, BaseWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BaseWebActivity.WEB_URL, url);
        bundle.putString(BaseWebActivity.WEB_DESC, desc);
        bundle.putString(BaseWebActivity.WEB_AUTHOR, author);
        bundle.putBoolean(IS_PATTERN, isPattern);
        intent.putExtras(bundle);

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, shareView, PictureActivity.TRANSIT_PIC);
        ActivityCompat.startActivity(activity, intent, compat.toBundle());
    }

    @Override
    public void initData(Bundle bundle)
    {
        if (bundle == null)
        {
            return;
        }
        url = bundle.getString(WEB_URL);
        descTitle = bundle.getString(WEB_DESC);
        author = bundle.getString(WEB_AUTHOR);
        isPat = bundle.getBoolean(IS_PATTERN, false);
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
        boolean isLike = MzApplication.mPref.get(url, false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));

        final ActionMenuItemView likeItem = (ActionMenuItemView) toolbar.findViewById(R.id.like);
        if (isLike)
        {
            likeItem.setIcon(getResources().getDrawable(R.mipmap.likedm));

        } else
        {

            likeItem.setIcon(getResources().getDrawable(R.mipmap.pre_like));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.refresh:
                        if (mAgentWeb != null)
                        {
                            mAgentWeb.getLoader().reload();
                        }
                        break;
                    case R.id.copy:
                        if (mAgentWeb != null)
                        {
                            toCopy(BaseWebActivity.this, mAgentWeb.getWebCreator().get().getUrl());
                        }
                        break;
                    case R.id.default_browser:
                        if (mAgentWeb != null)
                        {
                            openBrowser(mAgentWeb.getWebCreator().get().getUrl());
                        }
                        break;
                    case R.id.like:
                        boolean isLike = MzApplication.mPref.get(url, false);
                        if (isLike)
                        {
                            deleteFromPhone();
                            likeItem.setIcon(getResources().getDrawable(R.mipmap.pre_like));
                            MzApplication.mPref.put(url, false);
                        } else
                        {
                            saveToPhone();
                            likeItem.setIcon(getResources().getDrawable(R.mipmap.likedm));
                            Constant.IS_CLEAR = false;
                            MzApplication.mPref.put(url, true);
                        }
                        break;
                    case R.id.share:
                        Shares.share(BaseWebActivity.this, url);
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    /**
     * 理应异步处理
     */
    private void deleteFromPhone()
    {
        MyLikeEntity myLikeEntity = new MyLikeEntity();
        myLikeEntity.setUrl(url);
        myLikeEntity.setDesc(descTitle);
        myLikeEntity.setAuthor(author);
        myLikeEntities = (List<MyLikeEntity>) MzApplication.cache.getAsObject(Constant.MY_LIKE_DATA);
        if (myLikeEntities == null)
        {
            myLikeEntities = new ArrayList<>();
        }
        ArrayList<MyLikeEntity> entities = (ArrayList<MyLikeEntity>) ((ArrayList<MyLikeEntity>) myLikeEntities).clone();

        for (MyLikeEntity entity : myLikeEntities)
        {
            if (entity.getUrl().equals(url))
            {
                entities.remove(entity);
            }
        }

//        myLikeEntities.remove(myLikeEntity);
        MzApplication.cache.put(Constant.MY_LIKE_DATA, (Serializable) entities);
        EventBus.getDefault().post(entities);
        Toasts.showSingleShort(R.string.cancel_success);
    }

    /**
     * 理应异步处理
     */
    private void saveToPhone()
    {
        MyLikeEntity myLikeEntity = new MyLikeEntity();
        myLikeEntity.setUrl(url);
        myLikeEntity.setDesc(descTitle);
        myLikeEntity.setAuthor(author);
        myLikeEntities = (List<MyLikeEntity>) MzApplication.cache.getAsObject(Constant.MY_LIKE_DATA);
        if (myLikeEntities == null)
        {
            myLikeEntities = new ArrayList<>();
        }
        for (MyLikeEntity entity : myLikeEntities)
        {
            if (entity.getUrl().equals(url))
            {
                return;
            }
        }

        myLikeEntities.add(myLikeEntity);
        EventBus.getDefault().post(myLikeEntities);
        MzApplication.cache.put(Constant.MY_LIKE_DATA, (Serializable) myLikeEntities);
        Toasts.showSingleShort(R.string.save_success);
    }

    /**
     * 复制浏览器地址
     *
     * @param context
     * @param text
     */
    private void toCopy(Context context, String text)
    {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
        Toasts.showSingleLong("成功复制链接：" + text);
    }

    /**
     * 外部浏览器打开地址
     *
     * @param targetUrl
     */
    private void openBrowser(String targetUrl)
    {
        if (!TextUtils.isEmpty(targetUrl) && targetUrl.startsWith("file://"))
        {
            AgentWebUtils.toastShowShort(this, targetUrl + " 该链接无法使用浏览器打开。");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri url = Uri.parse(targetUrl);
        intent.setData(url);
        startActivity(intent);
    }

    @Override
    public void doBusiness(Context context)
    {
        if (!isPat)
        {
            mAgentWeb = AgentWeb.with(this)//传入Activity
                    //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                    .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()// 使用默认进度条
                    .setIndicatorColor(R.color.colorPrimary)
//                .defaultProgressBarColor()// 使用默认进度条颜色
                    .setReceivedTitleCallback(mCallback)//设置 Web 页面的 title 回调
                    .setWebChromeClient(mWebChromeClient)
                    .setWebViewClient(mWebViewClient)
                    .setSecutityType(AgentWeb.SecurityType.strict)
//                .setWebLayout(new WebLayout(this))
                    .createAgentWeb()//
                    .ready()
                    .go(url);
        } else
        {
            io.reactivex.Observable.create(new ObservableOnSubscribe<String>()
            {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception
                {
                    String htmlStr = getHtmlStr();
                    e.onNext(htmlStr);
                }
            }).compose(RxTransUtil.<String>rxSchedulerHelper())
                    .subscribe(new Consumer<String>()
                    {
                        @Override
                        public void accept(String s) throws Exception
                        {
                            String css1 = "<link href=\"http://chuansong.me/static/css/inspector.css\" rel=\"stylesheet\" type=\"text/css\">";
                            String css2 = "\n" +
                                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://chuansong.me/static/css/article_improve.css\"/>";
                            String body = "<head><style>img{max-width:100%}table{width:100%;}" + css1 + css2 + "</style></head>" + "<body>" + s + "</body>";
                            webView = new WebView(BaseWebActivity.this);
                            webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT);
                            webView.setLayoutParams(params);
                            container.addView(webView);
                        }
                    }, new Consumer<Throwable>()
                    {
                        @Override
                        public void accept(Throwable throwable) throws Exception
                        {

                        }
                    });
        }

    }

    private StringBuffer html;
    private String str;//网页源代码
    private String mContent;

    private String getHtmlStr()
    {
        html = new StringBuffer();
        URL mUrl = null;
        try
        {
            mUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) mUrl
                    .openConnection();
            InputStreamReader isr = new InputStreamReader(conn
                    .getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null)
            {
                html.append(temp).append("\n");
            }
            br.close();
            isr.close();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        str = html.toString();
        LogUtils.e("yixun-xk", "getHtmlStr()" + str);
        int start = str.indexOf("<div id=\"img-content\">");
        int end = str.indexOf("<div class=\"rich_media_tool\"");
        str = str.substring(start, end);
        LogUtils.e("yixun-xk", "getHtmlStr()" + str);
        mContent = str;
//        String pattern = "(?<=\"page-content\">).*(?=<div class=\"rich_media_tool\")";// 零宽断言
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(str);
//        if (m.find())
//        {
//            mContent = m.group();
//        }
        return mContent;
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
            {
                toolbarTitle.setText(title);
            }
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient()
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {

        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient()
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (mAgentWeb != null)
        {
            if (mAgentWeb.handleKeyEvent(keyCode, event))
            {
                return true;
            }
        }
        if (webView != null && webView.canGoBack())
        {
            webView.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}
