package com.exsun.meizi.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;

import java.io.File;

/**
 * Created by xiaokun on 2017/9/12.
 * 图片加载工具类 使用glide框架封装
 */

public class ImageLoaderUtils
{
    public static void display(Context context, ImageView imageView, String url, int placeholder, int error)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, int url)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, File file)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(file)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, byte[] bytes)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(bytes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }

    public static void displaySize(Context context, ImageView imageView, String url, int width, int height)
    {
        if (imageView == null)
        {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(MzApplication.getAppContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade()
                .override(width, height)
                .into(imageView);
    }

}
