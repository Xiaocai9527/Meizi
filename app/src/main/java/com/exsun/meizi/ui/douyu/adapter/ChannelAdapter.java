package com.exsun.meizi.ui.douyu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.RoomsEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class ChannelAdapter extends CommonAdapter<RoomsEntity.DataBean>
{

    public ChannelAdapter(Context context, int layoutId, List<RoomsEntity.DataBean> datas)
    {
        super(context, layoutId, datas);
    }

    public void updateData(List<RoomsEntity.DataBean> dataBeen)
    {
        mDatas = dataBeen;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, RoomsEntity.DataBean dataBean, int position)
    {
        ImageView imageView = holder.getView(R.id.image_room);
        TextView roomInfo = holder.getView(R.id.textview_room_info);
        TextView roomTitle = holder.getView(R.id.textview_room_title);

        ImageLoaderUtils.display(mContext, imageView, dataBean.getRoom_src());
        roomInfo.setText(dataBean.getNickname() + " · " + dataBean.getOnline() + "人正在观看");
        roomTitle.setText(dataBean.getRoom_name());

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // jump to live activity
            }
        });
    }
}
