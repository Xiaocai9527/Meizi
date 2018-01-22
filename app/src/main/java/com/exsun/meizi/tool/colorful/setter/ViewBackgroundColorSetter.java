package com.exsun.meizi.tool.colorful.setter;

import android.view.View;
import android.content.res.Resources.Theme;

/**
 * Created by 肖坤 on 2017/9/12.
 * 公司：依迅北斗
 * 邮箱：838494268@qq.com
 */

public class ViewBackgroundColorSetter extends ViewSetter
{
    public ViewBackgroundColorSetter(View target, int resId)
    {
        super(target, resId);
    }

    public ViewBackgroundColorSetter(int viewId, int resId)
    {
        super(viewId, resId);
    }

    @Override
    public void setValue(Theme newTheme, int themeId)
    {
        if (mView != null)
        {
            mView.setBackgroundColor(getColor(newTheme));
        }
    }
}
