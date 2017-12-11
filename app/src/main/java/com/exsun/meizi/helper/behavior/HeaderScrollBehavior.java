package com.exsun.meizi.helper.behavior;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.OverScroller;

import com.exsun.meizi.R;

import java.lang.ref.WeakReference;

/**
 * @author xiaokun
 * @date 2017/12/11
 */

public class HeaderScrollBehavior extends CoordinatorLayout.Behavior<View>
{
    public static final String TAG = "HeaderScrollBehavior";

    private WeakReference<View> mDependencyView;

    private OverScroller mOverScroller;

    private Handler mHandler;

    private boolean isExpand = false;

    private boolean isScrolling = false;

    public HeaderScrollBehavior(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        mOverScroller = new OverScroller(context);
        mHandler = new Handler();

    }

    /**
     * 表示是否给应用了Behavior 的View 指定一个依赖的布局，通常，当依赖的View 布局发生变化时
     * 不管被被依赖View 的顺序怎样，被依赖的View也会重新布局
     *
     * @param parent
     * @param child      绑定behavior 的View
     * @param dependency 依赖的view
     * @return 如果child 是依赖的指定的View 返回true,否则返回false
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
    {
        if (isDepend(dependency))
        {
            Log.i("zhouwei", "isDeoendent : true");
            mDependencyView = new WeakReference<View>(dependency);
            return true;
        }

        return false;
    }

    /**
     * 当被依赖的View 状态（如：位置、大小）发生变化时，这个方法被调用
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
    {
        Resources resources = getDependencyView().getResources();
        final float progress = 1.f - Math.abs(dependency.getTranslationY() / (dependency.getHeight()));
        Log.i(TAG, "dependency.getTranslationY():" + dependency.getTranslationY());
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());

        float scale = 1 + 0.2f * (1.f - progress);
        dependency.setScaleX(scale);
        dependency.setScaleY(scale);

        dependency.setAlpha(progress);


        return true;
    }

    //可以重写这个方法对子View 进行重新布局
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection)
    {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT)
        {
            child.layout(0, 0, parent.getWidth(), parent.getHeight() - getHeaderCollspateHeight());
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * 当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     * coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     * 的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     * 这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child             和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes  嵌套滑动 应用的滑动方向，看 {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                          {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * onStartNestedScroll返回true才会触发这个方法，接受滚动处理后回调，可以在这个
     * 方法里做一些准备工作，如一些状态的重置等。
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     */
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes)
    {
        isScrolling = false;
        mOverScroller.abortAnimation();
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 嵌套滚动发生之前被调用
     * 在nested scroll child 消费掉自己的滚动距离之前，嵌套滚动每次被nested scroll child
     * 更新都会调用onNestedPreScroll。注意有个重要的参数consumed，可以修改这个数组表示你消费
     * 了多少距离。假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
     * 这样coordinatorLayout就能知道只处理剩下的10px的滚动。
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx                用户水平方向的滚动距离
     * @param dy                用户竖直方向的滚动距离
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed)
    {
        Log.e(TAG, "--->invoke onNestedPreScroll");
        Log.i(TAG, "dy---->" + dy);
        if (dy < 0)
        {
            return;
        }
        View dependentView = getDependencyView();
        Log.i(TAG, "TranslationY():" + dependentView.getTranslationY() + " dy---->" + dy);
        float newTranslationY = dependentView.getTranslationY() - dy;
        float minHeaderTranslation = -(dependentView.getHeight() - getHeaderCollspateHeight());
        Log.i(TAG, "onNestedPreScroll:::newTranslationY:" + newTranslationY + "--->minHeaderTranslation" + minHeaderTranslation);
        if (newTranslationY > minHeaderTranslation)
        {
            dependentView.setTranslationY(newTranslationY);
            consumed[1] = dy;
        }
    }

    /**
     * 进行嵌套滚动时被调用
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed        target 已经消费的x方向的距离
     * @param dyConsumed        target 已经消费的y方向的距离
     * @param dxUnconsumed      x 方向剩下的滚动距离
     * @param dyUnconsumed      y 方向剩下的滚动距离
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed)
    {
        Log.e(TAG, "++++invoke onNestedScroll");
        Log.i(TAG, "dyUnconsumed:" + dyUnconsumed);
        if (dyUnconsumed > 0)
        {
            return;
        }
        View dependentView = getDependencyView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;
        Log.i(TAG, "onNestedScroll:::newTranslateY:" + newTranslateY + "--->maxHeaderTranslate" + maxHeaderTranslate);
        if (newTranslateY < maxHeaderTranslate)
        {
            dependentView.setTranslationY(newTranslateY);
        }
    }

    /**
     * 用户松开手指并且会发生惯性动作之前调用，参数提供了速度信息，可以根据这些速度信息
     * 决定最终状态，比如滚动Header，是让Header处于展开状态还是折叠状态。返回true 表
     * 示消费了fling.
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX         x 方向的速度
     * @param velocityY         y 方向的速度
     * @return
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY)
    {
        return onUserStopDragging(velocityY);
    }

    /**
     * 嵌套滚动结束时被调用，这是一个清除滚动状态等的好时机。
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target)
    {
        if (!isScrolling)
        {
            onUserStopDragging(800);
        }
    }

    private boolean onUserStopDragging(float velocity)
    {
        View dependentView = getDependencyView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getHeight() - getHeaderCollspateHeight());

        if (translateY == 0 || translateY == minHeaderTranslate)
        {
            return false;
        }

        boolean targetState; // Flag indicates whether to expand the content.
        if (Math.abs(velocity) <= 800)
        {
            if (Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate))
            {
                targetState = false;
            } else
            {
                targetState = true;
            }
            velocity = 800; // Limit velocity's minimum value.
        } else
        {
            if (velocity > 0)
            {
                targetState = true;
            } else
            {
                targetState = false;
            }
        }

        float targetTranslateY = targetState ? minHeaderTranslate : 0;
        mOverScroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (1000000 / Math.abs(velocity)));
        mHandler.post(flingRunnable);
        isScrolling = true;

        return true;
    }


    private View getDependencyView()
    {
        return mDependencyView.get();
    }

    /**
     * header 折叠高度
     *
     * @return
     */
    public int getHeaderCollspateHeight()
    {
        return 0;
    }

    public boolean isDepend(View dependency)
    {
        return dependency != null && dependency.getId() == R.id.header_view;
    }

    private Runnable flingRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            if (mOverScroller.computeScrollOffset())
            {
                getDependencyView().setTranslationY(mOverScroller.getCurrY());
                mHandler.post(this);
            } else
            {
                isExpand = getDependencyView().getTranslationX() != 0;
                isScrolling = false;
            }
        }
    };
}
