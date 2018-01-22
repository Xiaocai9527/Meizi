package com.exsun.meizi.ui.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.tool.ImageLoaderUtils;
import com.exsun.meizi.tool.Toasts;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/22
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class CircleViewBinder extends ItemViewBinder<TalkMoodEntity, CircleViewBinder.CircleViewHolder>
{

    @NonNull
    @Override
    protected CircleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent)
    {
        View view = inflater.inflate(R.layout.item_community, parent, false);
        return new CircleViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull CircleViewHolder holder, @NonNull TalkMoodEntity item)
    {
        holder.setItem(item);
    }

    static class CircleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @Bind(R.id.head_portrait)
        ImageView headPortrait;
        @Bind(R.id.category_author)
        TextView categoryAuthor;
        @Bind(R.id.category_date)
        TextView categoryDate;
        @Bind(R.id.category_desc)
        TextView categoryDesc;
        @Bind(R.id.location)
        TextView location;
        @Bind(R.id.cardview)
        CardView cardview;

        public CircleViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v)
        {

        }

        void setItem(TalkMoodEntity item)
        {
            ImageLoaderUtils.display(itemView.getContext(), headPortrait, R.mipmap.ic_launcher);
            categoryAuthor.setText(item.getNickName());
            categoryDate.setText("发表于 " + item.getPublishedTime());
            categoryDesc.setText(item.getContent());
            location.setText(item.getLocation());
        }

        @OnClick(R.id.cardview)
        public void onViewClicked()
        {
            Toasts.showSingleShort("点击动态跳转");
        }

    }


}
