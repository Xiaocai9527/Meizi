package com.exsun.meizi.ui.picture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.exsun.meizi.R;
import com.exsun.meizi.tool.ImageLoaderUtils;
import com.exsun.meizi.tool.RxMeizi;
import com.exsun.meizi.tool.Shares;
import com.exsun.meizi.tool.Toasts;
import com.yuyh.library.Base.BaseActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/7/28.
 */

public class PictureActivity extends BaseActivity
{
    public static final String URL = "picture_url";
    public static final String DESC = "picture_desc";
    public static final String TRANSIT_PIC = "picture";

    @Bind(R.id.picture_img)
    ImageView pictureImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String imgDesc;
    private String imgUrl;

    public static void jumpToPictureActivity(Activity activity, String url, String desc, View shareView)
    {
        Intent intent = new Intent(activity, PictureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(PictureActivity.URL, url);
        bundle.putString(PictureActivity.DESC, desc);
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
        imgUrl = bundle.getString(URL);
        imgDesc = bundle.getString(DESC);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_picture;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        toolbar.setTitle(imgDesc);
        toolbar.setTitleTextColor(Color.parseColor("#efefef"));
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.inflateMenu(R.menu.menu_picture);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.action_save:
                        saveImageToPhone();
                        break;
                    case R.id.action_share:
                        shareImage();
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
                onBackPressed();
//                finish();
            }
        });
        ViewCompat.setTransitionName(pictureImg, TRANSIT_PIC);
        ImageLoaderUtils.display(this, pictureImg, imgUrl);
    }

    private void saveImageToPhone()
    {
        if (pictureImg == null)
        {
            return;
        }
        RxMeizi.saveImageAndGetPathObservable(this, imgUrl, imgDesc).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Uri>()
                        {
                            @Override
                            public void accept(Uri uri) throws Exception
                            {
                                File appDir = new File(Environment.getExternalStorageDirectory(), "xiaocai_meizi");
                                String msg = String.format(getString(R.string.picture_has_save_to),
                                        appDir.getAbsolutePath());
                                Toasts.showSingleShort(msg);
                            }
                        }, new Consumer<Throwable>()
                        {
                            @Override
                            public void accept(Throwable throwable) throws Exception
                            {
                                Toasts.showSingleLong(throwable.getMessage() + "\n再试试...");
                            }
                        }
                );
    }

    private void shareImage()
    {
        RxMeizi.saveImageAndGetPathObservable(this, imgUrl, imgDesc).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Uri>()
                        {
                            @Override
                            public void accept(Uri uri) throws Exception
                            {
                                Shares.shareImage(PictureActivity.this, uri, getString(R.string.share_meizhi_to));
                            }
                        }, new Consumer<Throwable>()
                        {
                            @Override
                            public void accept(Throwable throwable) throws Exception
                            {
                                Toasts.showSingleLong(throwable.getMessage() + "\n再试试...");
                            }
                        }
                );
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @OnClick(R.id.picture_img)
    public void onViewClicked()
    {

    }

    @Override
    public void setStatusBar()
    {
//        super.setStatusBar();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (pictureImg != null)
        {
            pictureImg = null;
        }
    }
}
