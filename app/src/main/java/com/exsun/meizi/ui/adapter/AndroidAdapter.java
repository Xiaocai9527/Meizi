package com.exsun.meizi.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.GankCategoryEntity;
import com.exsun.meizi.ui.activity.BaseWebActivity;
import com.yuyh.library.utils.DimenUtils;
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
        TextView tv = holder.getView(R.id.category_desc);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
        layoutParams.height = (int) DimenUtils.dpToPx(70);
        tv.setLayoutParams(layoutParams);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextSize(16);
        tv.setText(text);
        holder.getView(R.id.category_img).setVisibility(View.GONE);

        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, BaseWebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BaseWebActivity.WEB_URL, resultsBean.getUrl());
                intent.putExtras(bundle);

                mContext.startActivity(intent);
            }
        });
    }
}
