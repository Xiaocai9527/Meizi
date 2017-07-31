package com.exsun.meizi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.exsun.meizi.R;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.yuyh.library.Base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

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
        ViewCompat.setTransitionName(pictureImg, TRANSIT_PIC);
        ImageLoaderUtils.display(this, pictureImg, imgUrl);
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    @OnClick(R.id.picture_img)
    public void onViewClicked()
    {

    }
}
