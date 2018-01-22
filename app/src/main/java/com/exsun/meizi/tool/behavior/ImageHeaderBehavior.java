package com.exsun.meizi.tool.behavior;

import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import com.exsun.meizi.R;

import java.lang.ref.WeakReference;

/**
 * @author xiaokun
 * @date 2017/12/12
 */

public class ImageHeaderBehavior extends CoordinatorLayout.Behavior<View>
{
    public static final String TAG = "ImageHeaderBehavior";
    private WeakReference<View> mDependencyView;

    public ImageHeaderBehavior()
    {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        if (isDepend(dependency))
        {
            mDependencyView = new WeakReference<View>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
    {
        Resources resources = getDependencyView().getResources();
        float progress = 1.0f - Math.abs(dependency.getTranslationY() / dependency.getHeight());
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());

        dependency.setAlpha(progress);
        return true;
    }

    private boolean isDepend(View dependency)
    {
        return dependency != null && dependency.getId() == R.id.header_view;
    }

    public View getDependencyView()
    {
        return mDependencyView.get();
    }
}
