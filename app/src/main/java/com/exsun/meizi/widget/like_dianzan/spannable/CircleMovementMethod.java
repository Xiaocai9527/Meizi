package com.exsun.meizi.widget.like_dianzan.spannable;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.BaseMovementMethod;
import android.text.method.Touch;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.yuyh.library.AppUtils;


/**
 * @author xiaokun
 * @date 2017/10/17
 */

public class CircleMovementMethod extends BaseMovementMethod
{
    public static final int DEFAULT_COLOR = R.color.transparent;
    private int mTextViewBgColorId;
    private int mClickableSpanBgColorId;

    /**
     * 背景颜色  通过Span来设置
     */
    private BackgroundColorSpan mBgSpan;
    /**
     * ClickableSpan数组
     */
    private ClickableSpan[] mClickLinks;
    /**
     * 是否传递事件给TextView的标志位
     */
    private boolean mIsPassToTv = true;

    public CircleMovementMethod()
    {
        mTextViewBgColorId = DEFAULT_COLOR;
        mClickableSpanBgColorId = DEFAULT_COLOR;
    }

    public CircleMovementMethod(int clickableSpanBgColorId)
    {
        mClickableSpanBgColorId = clickableSpanBgColorId;
        mTextViewBgColorId = DEFAULT_COLOR;
    }

    public CircleMovementMethod(int clickableSpanBgColorId, int textViewBgColorId)
    {
        mClickableSpanBgColorId = clickableSpanBgColorId;
        mTextViewBgColorId = textViewBgColorId;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable text, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                x = x - widget.getTotalPaddingLeft();
                y = y - widget.getTotalPaddingTop();

                x = x + widget.getScrollX();
                y = y + widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int offsetForHorizontal = layout.getOffsetForHorizontal(line, x);
                mClickLinks = text.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);

                if (mClickLinks.length > 0)
                {
                    //即表示点击的区域中包含有clickable，那么事件被Span区域消耗不传递给父控件textview
                    setPassToTv(false);
                    Selection.setSelection(text, text.getSpanStart(mClickLinks[0]), text.getSpanEnd(mClickLinks[0]));
                    //设置点击区域的背景色,点击的一瞬间变色，UP的时候再设置成透明色
                    mBgSpan = new BackgroundColorSpan(AppUtils.getAppContext().getResources().getColor(mClickableSpanBgColorId));
                    text.setSpan(mBgSpan, text.getSpanStart(mClickLinks[0]), text.getSpanEnd(mClickLinks[0]), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else
                {
                    setPassToTv(true);
                    //textview选中效果
                    widget.setBackgroundResource(mTextViewBgColorId);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mClickLinks.length > 0)
                {
                    mClickLinks[0].onClick(widget);
                    if (mBgSpan != null)
                    {
                        text.removeSpan(mBgSpan);
                    }
                } else
                {
                    if (mBgSpan != null)
                    {
                        text.removeSpan(mBgSpan);
                    }
                }
                Selection.removeSelection(text);
                widget.setBackgroundResource(DEFAULT_COLOR);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            default:
                if (mBgSpan != null)
                {
                    text.removeSpan(mBgSpan);
                }
                widget.setBackgroundResource(DEFAULT_COLOR);
                break;
        }
        return Touch.onTouchEvent(widget, text, event);
    }

    /**
     * 设置是否传递给textview
     *
     * @param isPassToTv 是否传递给textview
     */
    private void setPassToTv(boolean isPassToTv)
    {
        mIsPassToTv = isPassToTv;
    }

    /**
     * @return 返回布尔值，true代表将事件传递给父控件textview，反之span自己消耗
     */
    public boolean isPassToTv()
    {
        return mIsPassToTv;
    }
}
