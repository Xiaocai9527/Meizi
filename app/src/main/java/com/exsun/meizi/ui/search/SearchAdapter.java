package com.exsun.meizi.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.SearchEntity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class SearchAdapter extends CommonAdapter<SearchEntity.ResultsBean>
{

    public SearchAdapter(Context context, int layoutId, List<SearchEntity.ResultsBean> datas)
    {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final SearchEntity.ResultsBean resultsBean, int position)
    {
        int limit = 48;
        String string = resultsBean.getDesc();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;
        TextView desc = holder.getView(R.id.category_desc);
        TextView author = holder.getView(R.id.category_author);
        TextView date = holder.getView(R.id.category_date);
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
    }
}
