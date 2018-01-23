package com.exsun.meizi.widget.like_dianzan.spannable;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.exsun.meizi.R;
import com.yuyh.library.AppUtils;


/**
 * @author xiaokun
 * @date 2017/10/18
 */

public class NameClickable extends ClickableSpan implements View.OnClickListener
{

    private ISpanClick mListener;
    private int mPosition;

    public NameClickable(ISpanClick mListener, int mPosition)
    {
        this.mListener = mListener;
        this.mPosition = mPosition;
    }

    @Override
    public void onClick(View widget)
    {
        mListener.onClick(mPosition);
    }

    @Override
    public void updateDrawState(TextPaint ds)
    {
        super.updateDrawState(ds);
        int colorValue = AppUtils.getAppContext().getResources().getColor(R.color.colorPrimary);
        //设置颜色
        ds.setColor(colorValue);
        //设置不要下划线
        ds.setUnderlineText(false);
        //清除阴影层
        ds.clearShadowLayer();
    }
}
