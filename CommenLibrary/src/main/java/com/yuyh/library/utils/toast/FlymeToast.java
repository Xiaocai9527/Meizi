package com.yuyh.library.utils.toast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuyh.library.R;

/**
 * Created by xiaokun on 2017/6/26.
 */

public class FlymeToast
{
    private Context mContext;
    private WindowManager wm;
    private int mDuration;
    private View mNextView;
    public static final int LENGTH_SHORT = 1500;
    public static final int LENGTH_LONG = 3000;

    public FlymeToast(Context context)
    {
        mContext = context.getApplicationContext();
    }

    public static FlymeToast makeText(Context context, CharSequence text, int duration)
    {
        FlymeToast result = new FlymeToast(context);
        LayoutInflater inflate = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.transient_notification, null);
        TextView tv = (TextView) v.findViewById(R.id.message);
        tv.setText(text);
        result.mNextView = v;
        result.mDuration = duration;
        return result;
    }

    public static FlymeToast makeText(Context context, int resId, int duration) throws Resources.NotFoundException
    {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    public void show()
    {
        if (mNextView != null)
        {
            wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = R.style.Animation_FlymeToast;
            params.y = dip2px(mContext, 64);
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            wm.addView(mNextView, params);
            new Handler().postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    if (mNextView != null)
                    {
                        //每次显示完后，remove调view
                        wm.removeView(mNextView);
                        mNextView = null;
                        wm = null;
                    }
                }
            }, mDuration);
        }
    }

    public static int dip2px(Context context, float dipValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
