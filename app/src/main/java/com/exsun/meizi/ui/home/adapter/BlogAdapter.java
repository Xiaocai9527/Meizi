package com.exsun.meizi.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.gongzhonghao.BlogEntity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author xiaokun
 * @date 2017/12/13
 */

public class BlogAdapter extends CommonAdapter<BlogEntity>
{
    public BlogAdapter(Context context, int layoutId, List<BlogEntity> datas)
    {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final BlogEntity blogEntity, int position)
    {
        int limit = 48;
        final String string = blogEntity.getTitle();
        String text = string.length() > limit ? string.substring(0, limit) + "..." : string;
        final TextView desc = holder.getView(R.id.category_desc);
        final TextView author = holder.getView(R.id.category_author);
        TextView date = holder.getView(R.id.category_date);
        desc.setText(text);
        author.setText(blogEntity.getAuthor());
        date.setText(blogEntity.getTime().substring(0, 10));
        holder.setOnClickListener(R.id.home_cv, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BaseWebActivity.jumpToBaseWebActivity((BaseActivity) mContext, blogEntity.getUrl(),
                        string, blogEntity.getAuthor(), desc, true);
            }
        });
    }
}
