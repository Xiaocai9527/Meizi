package com.yuyh.library.utils.anim;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * @author yuyh.
 * @date 16/4/11.
 */
public class AnimationUtils
{

    /**
     * Don't let anyone instantiate this class.
     */
    private AnimationUtils()
    {
        throw new Error("Do not need instantiate!");
    }


    /**
     * 默认动画持续时间
     */
    public static final long DEFAULT_ANIMATION_DURATION = 400;


    /**
     * 获取一个旋转动画
     *
     * @param fromDegrees       开始角度
     * @param toDegrees         结束角度
     * @param pivotXType        旋转中心点X轴坐标相对类型
     * @param pivotXValue       旋转中心点X轴坐标
     * @param pivotYType        旋转中心点Y轴坐标相对类型
     * @param pivotYValue       旋转中心点Y轴坐标
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个旋转动画
     */
    public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue, long durationMillis, Animation.AnimationListener animationListener)
    {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,
                toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        rotateAnimation.setDuration(durationMillis);
        if (animationListener != null)
        {
            rotateAnimation.setAnimationListener(animationListener);
        }
        return rotateAnimation;
    }


    /**
     * 获取一个根据视图自身中心点旋转的动画
     *
     * @param durationMillis    动画持续时间
     * @param animationListener 动画监听器
     * @return 一个根据中心点旋转的动画
     */
    public static RotateAnimation getRotateAnimationByCenter(long durationMillis, Animation.AnimationListener animationListener)
    {
        return getRotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, durationMillis,
                animationListener);
    }


    /**
     * 获取一个根据中心点旋转的动画
     *
     * @param duration 动画持续时间
     * @return 一个根据中心点旋转的动画
     */
    public static RotateAnimation getRotateAnimationByCenter(long duration)
    {
        return getRotateAnimationByCenter(duration, null);
    }


    /**
     * 获取一个根据视图自身中心点旋转的动画
     *
     * @param animationListener 动画监听器
     * @return 一个根据中心点旋转的动画
     */
    public static RotateAnimation getRotateAnimationByCenter(Animation.AnimationListener animationListener)
    {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }


    /**
     * 获取一个根据中心点旋转的动画
     *
     * @return 一个根据中心点旋转的动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static RotateAnimation getRotateAnimationByCenter()
    {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION, null);
    }


    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha         开始时的透明度
     * @param toAlpha           结束时的透明度都
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个透明度渐变动画
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis, Animation.AnimationListener animationListener)
    {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(durationMillis);
        if (animationListener != null)
        {
            alphaAnimation.setAnimationListener(animationListener);
        }
        return alphaAnimation;
    }


    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha      开始时的透明度
     * @param toAlpha        结束时的透明度都
     * @param durationMillis 持续时间
     * @return 一个透明度渐变动画
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis)
    {
        return getAlphaAnimation(fromAlpha, toAlpha, durationMillis, null);
    }


    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha         开始时的透明度
     * @param toAlpha           结束时的透明度都
     * @param animationListener 动画监听器
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, Animation.AnimationListener animationListener)
    {
        return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,
                animationListener);
    }


    /**
     * 获取一个透明度渐变动画
     *
     * @param fromAlpha 开始时的透明度
     * @param toAlpha   结束时的透明度都
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha)
    {
        return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,
                null);
    }


    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis, Animation.AnimationListener animationListener)
    {
        return getAlphaAnimation(1.0f, 0.0f, durationMillis, animationListener);
    }


    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param durationMillis 持续时间
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis)
    {
        return getHiddenAlphaAnimation(durationMillis, null);
    }


    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getHiddenAlphaAnimation(Animation.AnimationListener animationListener)
    {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }


    /**
     * 获取一个由完全显示变为不可见的透明度渐变动画
     *
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getHiddenAlphaAnimation()
    {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION, null);
    }


    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public static AlphaAnimation getShowAlphaAnimation(long durationMillis, Animation.AnimationListener animationListener)
    {
        return getAlphaAnimation(0.0f, 1.0f, durationMillis, animationListener);
    }


    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param durationMillis 持续时间
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public static AlphaAnimation getShowAlphaAnimation(long durationMillis)
    {
        return getAlphaAnimation(0.0f, 1.0f, durationMillis, null);
    }


    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getShowAlphaAnimation(Animation.AnimationListener animationListener)
    {
        return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION,
                animationListener);
    }


    /**
     * 获取一个由不可见变为完全显示的透明度渐变动画
     *
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getShowAlphaAnimation()
    {
        return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION, null);
    }


    /**
     * 获取一个缩小动画
     *
     * @param durationMillis    时间
     * @param animationListener 监听
     * @return 一个缩小动画
     */
    public static ScaleAnimation getLessenScaleAnimation(long durationMillis, Animation.AnimationListener animationListener)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setAnimationListener(animationListener);

        return scaleAnimation;
    }


    /**
     * 获取一个缩小动画
     *
     * @param durationMillis 时间
     * @return 一个缩小动画
     */
    public static ScaleAnimation getLessenScaleAnimation(long durationMillis)
    {
        return getLessenScaleAnimation(durationMillis, null);

    }


    /**
     * 获取一个缩小动画
     *
     * @param animationListener 监听
     * @return 返回一个缩小的动画
     */
    public static ScaleAnimation getLessenScaleAnimation(Animation.AnimationListener animationListener)
    {
        return getLessenScaleAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);

    }


    /**
     * 获取一个放大动画
     *
     * @param durationMillis    时间
     * @param animationListener 监听
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(long durationMillis, Animation.AnimationListener animationListener)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f,
                1.0f, ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }


    /**
     * 获取一个放大动画
     *
     * @param durationMillis 时间
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(long durationMillis)
    {
        return getAmplificationAnimation(durationMillis, null);

    }


    /**
     * 获取一个放大动画
     *
     * @param animationListener 监听
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(Animation.AnimationListener animationListener)
    {
        return getAmplificationAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);

    }
}
