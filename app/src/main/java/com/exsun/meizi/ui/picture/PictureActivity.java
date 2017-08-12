package com.exsun.meizi.ui.picture;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.exsun.meizi.R;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.helper.RxMeizi;
import com.exsun.meizi.helper.Shares;
import com.exsun.meizi.helper.Toasts;
import com.yuyh.library.Base.BaseActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
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
                .subscribe(new Observer<Uri>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(Uri value)
                    {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "xiaocai_meizi");
                        String msg = String.format(getString(R.string.picture_has_save_to),
                                appDir.getAbsolutePath());
                        Toasts.showSingleShort(msg);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Toasts.showSingleLong(e.getMessage() + "\n再试试...");
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
    }

    private void shareImage()
    {
        RxMeizi.saveImageAndGetPathObservable(this, imgUrl, imgDesc).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Uri>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(Uri value)
                    {
                        Shares.shareImage(PictureActivity.this, value, getString(R.string.share_meizhi_to));
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Toasts.showSingleLong(e.getMessage() + "\n再试试...");
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
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
