package com.exsun.meizi.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.gank.HomeMixEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.ui.picture.PictureActivity;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class MzRvAdapter extends CommonAdapter<HomeMixEntity>
{

    public MzRvAdapter(Context context, int layoutId, List<HomeMixEntity> datas)
    {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final HomeMixEntity homeMixEntity, int position)
    {
        int limit = 48;
        String string = homeMixEntity.getDate() + "  " + homeMixEntity.getDesc();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;
        TextView tv = holder.getView(R.id.category_desc);
        final ImageView img = holder.getView(R.id.category_img);
        CardView cv = holder.getView(R.id.home_cv);

        ImageLoaderUtils.display(mContext, img, homeMixEntity.getUrl());
        tv.setText(text);

        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, PictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(PictureActivity.URL, homeMixEntity.getUrl());
                bundle.putString(PictureActivity.DESC, homeMixEntity.getDesc());
                intent.putExtras(bundle);

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (BaseActivity) mContext, img, PictureActivity.TRANSIT_PIC);
                ActivityCompat.startActivity(mContext, intent, compat.toBundle());
//                ((HomeActivity) mContext).startActivity(PictureActivity.class, bundle);
            }
        });

        cv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }
}
