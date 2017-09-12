package com.exsun.meizi.helper.colorful.setter;

import android.widget.TextView;
import android.content.res.Resources.Theme;

/**
 * Created by xiaokun on 2017/9/12.
 */

public class TextColorSetter extends ViewSetter
{
    public TextColorSetter(TextView textView, int resId)
    {
        super(textView, resId);
    }

    public TextColorSetter(int viewId, int resId)
    {
        super(viewId, resId);
    }

    @Override
    public void setValue(Theme newTheme, int themeId)
    {
        if (mView == null)
        {
            return;
        }
        ((TextView) mView).setTextColor(getColor(newTheme));
    }
}
