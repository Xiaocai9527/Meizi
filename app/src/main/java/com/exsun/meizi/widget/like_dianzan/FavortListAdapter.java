package com.exsun.meizi.widget.like_dianzan;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.tool.utils;
import com.exsun.meizi.widget.CommenAdapter;
import com.exsun.meizi.widget.like_dianzan.spannable.CircleMovementMethod;
import com.exsun.meizi.widget.like_dianzan.spannable.NameClickable;
import com.yuyh.library.utils.DimenUtils;

import java.util.List;


/**
 * 点赞列表适配器
 *
 * @author xiaokun
 * @date 2017/10/18
 */

public class FavortListAdapter extends CommenAdapter<MyUser>
{
    public FavortListAdapter(Context context)
    {
        super(context);
    }

    public FavortListAdapter(Context context, List<MyUser> datas)
    {
        super(context, datas);
    }

    @Override
    public void notifyDataSetChanged()
    {
        if (mListView2 == null)
        {
            throw new NullPointerException("mListView2 is null, please bindView first...");
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (mDatas != null && mDatas.size() > 0)
        {
            //首先添加一个点赞爱心
//            builder.append(setImgSpan());

            int size = mDatas.size();
            MyUser item = null;
            for (int i = 0; i < size; i++)
            {
                item = mDatas.get(i);
                if (item != null)
                {
                    builder.append(setClickableSpan(item.getNickName(), i));
                    if (i != mDatas.size() - 1)
                    {
                        builder.append(", ");
                    }
                }
            }
        }
        utils.setTextDrawable((FavortListView) mListView2, mListView2.getContext(), R.mipmap.zaned, utils.LEFT, DimenUtils.dpToPxInt(3));
        ((FavortListView) mListView2).setText(builder);
        //span能点击的关键方法
        ((FavortListView) mListView2).setMovementMethod(new CircleMovementMethod(R.color.circle_name_selector_color));
    }

    private SpannableString setClickableSpan(String textStr, int position)
    {
        SpannableString spannableString = new SpannableString(textStr);
        spannableString.setSpan(new NameClickable(((FavortListView) mListView2).getSpanClickListener(), position),
                0, spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置图片span
     *
     * @return
     */
    private SpannableString setImgSpan()
    {
        String text = " ";
        SpannableString imgSpanText = new SpannableString(text);
        imgSpanText.setSpan(new ImageSpan(mContext, R.mipmap.zaned, DynamicDrawableSpan.ALIGN_BASELINE),
                0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return imgSpanText;
    }
}
