package com.exsun.meizi.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/04
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class CTextView extends ViewGroup
{
    private Context context;

    public CTextView(Context context)
    {
        super(context);
        this.context = context;
    }

    public CTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
    }

    public CTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void setText(String text, final Animation animation, int duration)
    {
        int time = 0;
        if (text != null && !text.isEmpty())
        {
            char[] characters = text.toCharArray();
            for (char c : characters)
            {
                final TextView t = new TextView(context);
                //遍历传入的字符串的每个字符，生成一个TextView，并设置它的动画
                t.setText(String.valueOf(c));
                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                Handler h = new Handler();
                //每隔duration时间，播放下一个TextView的动画
                h.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        addView(t);
                        t.setAnimation(animation);
                    }
                }, time);

                time += duration;

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int measureWidth = measureWidth(widthMeasureSpec);
        int measureHeight = measureHeight(heightMeasureSpec);
        // 计算自定义的ViewGroup中所有子控件的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 设置自定义的控件MyViewGroup的大小
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int childLeft = 0;
        // 遍历所有子视图
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);

            // 获取在onMeasure中计算的视图尺寸
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();

            //将他们横向排列
            childView.layout(childLeft, 0, childLeft + measuredWidth, measureHeight);

            childLeft += measuredWidth;
        }
    }

    private int measureWidth(int pWidthMeasureSpec)
    {
        int result = 0;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);// 得到模式
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);// 得到尺寸

        switch (widthMode)
        {
            /**
             * mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY,
             * MeasureSpec.AT_MOST。
             *
             *
             * MeasureSpec.EXACTLY是精确尺寸，
             * 当我们将控件的layout_width或layout_height指定为具体数值时如andorid
             * :layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
             *
             *
             * MeasureSpec.AT_MOST是最大尺寸，
             * 当控件的layout_width或layout_height指定为WRAP_CONTENT时
             * ，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可
             * 。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
             *
             *
             * MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，
             * 通过measure方法传入的模式。
             */
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int pHeightMeasureSpec)
    {
        int result = 0;

        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);

        switch (heightMode)
        {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }
}
