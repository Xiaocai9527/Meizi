package com.exsun.meizi.widget.media;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaokun on 2017/8/29.
 */

public class Setting
{
    public static final int PV_PLAYER__AndroidMediaPlayer = 1;
    public static final int PV_PLAYER__Auto = 0;
    public static final int PV_PLAYER__IjkExoMediaPlayer = 3;
    public static final int PV_PLAYER__IjkMediaPlayer = 2;
    public static boolean enableBackgroundPlay = true;
    public static boolean enableDetachedSurfaceTextureView = false;
    public static boolean enableNoView = false;
    public static boolean enableSurfaceView = false;
    public static boolean enableTextureView = false;
    public static boolean mediaCodecHandleResolutionChange = false;
    public static String pixelFormat = "";
    public static int player = 2;
    public static boolean usingMediaCodec = false;
    public static boolean usingMediaCodecAutoRotate = false;
    public static boolean usingMediaDataSource = false;
    public static boolean usingOpenSLES = false;
    private Context mAppContext;
    private SharedPreferences mSharedPreferences;

    public static boolean getEnableBackgroundPlay()
    {
        return enableBackgroundPlay;
    }

    public static void setEnableBackgroundPlay(boolean enableBackgroundPlay)
    {
        enableBackgroundPlay = enableBackgroundPlay;
    }

    public static int getPlayer()
    {
        return player;
    }

    public static void setPlayer(int player)
    {
        player = player;
    }

    public static boolean getUsingMediaCodec()
    {
        return usingMediaCodec;
    }

    public static void setUsingMediaCodec(boolean usingMediaCodec)
    {
        usingMediaCodec = usingMediaCodec;
    }

    public static boolean getUsingMediaCodecAutoRotate()
    {
        return usingMediaCodecAutoRotate;
    }

    public static void setUsingMediaCodecAutoRotate(boolean usingMediaCodecAutoRotate)
    {
        usingMediaCodecAutoRotate = usingMediaCodecAutoRotate;
    }

    public static boolean getMediaCodecHandleResolutionChange()
    {
        return mediaCodecHandleResolutionChange;
    }

    public static void setMediaCodecHandleResolutionChange(boolean mediaCodecHandleResolutionChange)
    {
        mediaCodecHandleResolutionChange = mediaCodecHandleResolutionChange;
    }

    public static boolean getUsingOpenSLES()
    {
        return usingOpenSLES;
    }

    public static void setUsingOpenSLES(boolean usingOpenSLES)
    {
        usingOpenSLES = usingOpenSLES;
    }

    public static String getPixelFormat()
    {
        return pixelFormat;
    }

    public static void setPixelFormat(String pixelFormat)
    {
        pixelFormat = pixelFormat;
    }

    public static boolean getEnableNoView()
    {
        return enableNoView;
    }

    public static void setEnableNoView(boolean enableNoView)
    {
        enableNoView = enableNoView;
    }

    public static boolean getEnableSurfaceView()
    {
        return enableSurfaceView;
    }

    public static void setEnableSurfaceView(boolean enableSurfaceView)
    {
        enableSurfaceView = enableSurfaceView;
    }

    public static boolean getEnableTextureView()
    {
        return enableTextureView;
    }

    public static void setEnableTextureView(boolean enableTextureView)
    {
        enableTextureView = enableTextureView;
    }

    public static boolean getEnableDetachedSurfaceTextureView()
    {
        return enableDetachedSurfaceTextureView;
    }

    public static void setEnableDetachedSurfaceTextureView(boolean enableDetachedSurfaceTextureView)
    {
        enableDetachedSurfaceTextureView = enableDetachedSurfaceTextureView;
    }

    public static boolean getUsingMediaDataSource()
    {
        return usingMediaDataSource;
    }

    public static void setUsingMediaDataSource(boolean usingMediaDataSource)
    {
        usingMediaDataSource = usingMediaDataSource;
    }

    public Setting(Context context)
    {
    }
}
