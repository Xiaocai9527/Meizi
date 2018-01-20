package com.exsun.meizi.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.gongzhonghao.BlogEntity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.yuyh.library.Base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaokun
 * @date 2017/12/13
 */

public class BlogAdapter extends CommonAdapter<BlogEntity> implements Filterable
{
    private List<BlogEntity> mFilteredList;

    public BlogAdapter(Context context, int layoutId, List<BlogEntity> datas)
    {
        super(context, layoutId, datas);
    }

    public void setNewData(List<BlogEntity> list)
    {
        mDatas = list;
        notifyDataSetChanged();
        mFilteredList = list;
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

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {

                String charString = charSequence.toString();

                if (charString.isEmpty())
                {
                    mDatas = mFilteredList;
                } else
                {
                    List<BlogEntity> filteredList = new ArrayList<>();

                    for (BlogEntity blogEntity : mFilteredList)
                    {
                        String desc = blogEntity.getTitle();
                        String publishedAt = blogEntity.getTime();
                        String who = blogEntity.getAuthor();
                        if (desc == null)
                        {
                            desc = "";
                        }
                        if (publishedAt == null)
                        {
                            publishedAt = "";
                        }
                        if (who == null)
                        {
                            who = "";
                        }
                        if (desc.contains(charString) || publishedAt.contains(charString) || who.contains(charString))
                        {
                            filteredList.add(blogEntity);
                        }
                    }

                    mDatas = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDatas;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                mDatas = (List<BlogEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
