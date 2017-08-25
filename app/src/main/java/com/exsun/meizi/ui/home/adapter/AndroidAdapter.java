package com.exsun.meizi.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/2.
 */

public class AndroidAdapter extends CommonAdapter<GankCategoryEntity.ResultsBean>
{
    public AndroidAdapter(Context context, int layoutId, List<GankCategoryEntity.ResultsBean> datas)
    {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final GankCategoryEntity.ResultsBean resultsBean, int position)
    {
        int limit = 48;
        String string = resultsBean.getDesc();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;
        TextView desc = holder.getView(R.id.category_desc);
        TextView author = holder.getView(R.id.category_author);
        TextView date = holder.getView(R.id.category_date);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
//        layoutParams.height = (int) DimenUtils.dpToPx(70);
//        tv.setLayoutParams(layoutParams);
//        tv.setGravity(Gravity.CENTER_VERTICAL);
//        tv.setTextSize(16);
        desc.setText(text);
        author.setText(resultsBean.getWho());
        date.setText(resultsBean.getPublishedAt().substring(0, 10));

        holder.setOnClickListener(R.id.home_cv, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, BaseWebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BaseWebActivity.WEB_URL, resultsBean.getUrl());
                bundle.putString(BaseWebActivity.WEB_DESC, resultsBean.getDesc());
                bundle.putString(BaseWebActivity.WEB_AUTHOR, resultsBean.getWho());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

//        desc.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(mContext, BaseWebActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString(BaseWebActivity.WEB_URL, resultsBean.getUrl());
//                intent.putExtras(bundle);
//
//                mContext.startActivity(intent);
//            }
//        });
    }
}
