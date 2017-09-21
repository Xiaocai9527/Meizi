package com.allen.playerview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLNetworkManager;
import com.pili.pldroid.player.widget.PLVideoTextureView;

import java.net.UnknownHostException;

/**
 * Created by Allen on 2017/3/24.
 * <p>
 * 视频播放组件
 */

public class VideoPlayerView extends LinearLayout
{


    private Context mContext;
    private Activity mActivity;
    private Toast mToast = null;

    private PLVideoTextureView playerView;

    /**
     * 视频地址
     */
    private String videoPath = null;
    /**
     * 是否自动播放
     */
    private boolean isStartVideo = false;
    /**
     * 是否重复播放
     */
    private boolean repeatPlay = false;
    /**
     * 是否显示底部播放控制布局
     */
    private boolean isShowPlayerBottomBar = false;
    /**
     * 是否显示加载loading
     */
    private boolean isShowLoadingView = true;
    /**
     * 是否显示视频全屏按钮
     */
    private boolean isShowFullScreenBtn = false;
    /**
     * 是否允许点击屏幕暂停播放
     */
    private boolean isTouchForPause = true;

    /**
     * 是否显示中间视频播放按钮
     */
    private boolean isShowCenterPlayerBtn = true;

    /**
     * 封面图片
     */
    private Drawable coverViewDrawable = null;
    /**
     * 视频画面预览模式    16：9    4：3  适应屏幕  等等
     */
    private int aspectRatio;


    private LinearLayout mPlayerBottomBarLayout;
    private ImageView mCoverView;
    private ProgressBar mLoadingView;

    private ImageView mPlayControllerIv;
    private ImageView mCenterPlayerBtnIv;
    private ImageView mFullScreenBtnIv;

    private TextView mCurrentTimeTv, mTotalTimeTv;
    private SeekBar mSeekBar;

    private FrameLayout mTouchForPauseView;

    private static final int UPDATE_UI = 1;

    public static final int ORIGIN = 0;
    public static final int FIT_PARENT = 1;
    public static final int PAVED_PARENT = 2;
    public static final int IS_16_9 = 3;
    public static final int FIS_4_3 = 4;


    /**
     * 视频播放完毕回调
     */
    public OnVideoCompletionListener mOnVideoCompletionListener;

    /**
     * 进度变化回调
     */
    public OnCurrentProgressChange mOnCurrentProgressChange;


    public VideoPlayerView(Context context)
    {
        this(context, null);
    }

    public VideoPlayerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        getAttr(attrs);
        initView();
        initPlayerView();
    }


    /**
     * 获取自定义属性
     *
     * @param attributeSet
     */
    private void getAttr(AttributeSet attributeSet)
    {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.VideoPlayerView);
        videoPath = typedArray.getString(R.styleable.VideoPlayerView_player_videoPath);
        isStartVideo = typedArray.getBoolean(R.styleable.VideoPlayerView_player_isStart, false);
        repeatPlay = typedArray.getBoolean(R.styleable.VideoPlayerView_player_repeatPlay, false);
        isShowPlayerBottomBar = typedArray.getBoolean(R.styleable.VideoPlayerView_player_isShowPlayerBottomBar, false);
        isShowLoadingView = typedArray.getBoolean(R.styleable.VideoPlayerView_player_isShowLoadingView, true);
        isShowFullScreenBtn = typedArray.getBoolean(R.styleable.VideoPlayerView_player_isShowFullScreenBtn, false);
        isTouchForPause = typedArray.getBoolean(R.styleable.VideoPlayerView_player_isTouchForPause, true);
        coverViewDrawable = typedArray.getDrawable(R.styleable.VideoPlayerView_player_coverViewDrawableId);
        aspectRatio = typedArray.getInt(R.styleable.VideoPlayerView_player_aspectRatio, 1);

        typedArray.recycle();
    }


    /**
     * 初始化view
     */
    private void initView()
    {
        LayoutInflater.from(mContext).inflate(R.layout.video_player_view_layout, this);

        mPlayerBottomBarLayout = (LinearLayout) findViewById(R.id.player_view_bottom_bar_layout_ll);

        playerView = (PLVideoTextureView) findViewById(R.id.VideoView);
        mCoverView = (ImageView) findViewById(R.id.player_view_cover_view);
        mLoadingView = (ProgressBar) findViewById(R.id.player_view_loading_view);

        mTouchForPauseView = (FrameLayout) findViewById(R.id.touch_for_pause_view);
        mPlayControllerIv = (ImageView) findViewById(R.id.player_view_play_controller_iv);
        mCurrentTimeTv = (TextView) findViewById(R.id.player_view_current_time_tv);
        mTotalTimeTv = (TextView) findViewById(R.id.player_view_total_time_tv);
        mSeekBar = (SeekBar) findViewById(R.id.player_view_current_seekBar_sb);
        mCenterPlayerBtnIv = (ImageView) findViewById(R.id.player_view_center_play_iv);
        mFullScreenBtnIv = (ImageView) findViewById(R.id.player_view_play_full_iv);

        //设置按钮事件
        setClickListenerEvent();

    }

    /**
     * 设置按钮事件
     */
    private void setClickListenerEvent()
    {
        mTouchForPauseView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isShowCenterPlayerBtn)
                {
                    if (isTouchForPause)
                    {
                        playController();
                    }
                }
            }
        });
        mPlayControllerIv.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playController();
            }
        });

        mCenterPlayerBtnIv.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                start();
            }
        });


        if (isShowFullScreenBtn)
        {

            mFullScreenBtnIv.setVisibility(VISIBLE);

            mFullScreenBtnIv.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    if (mActivity != null)
                    {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                if (mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                                {
                                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                } else
                                {
                                    mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                }
                            }
                        }, 10);
                    }
                }
            });

        } else
        {
            mFullScreenBtnIv.setVisibility(GONE);
        }


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                updateTextViewWithTimeFormat(mCurrentTimeTv, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                playerView.pause();
                TimeUIHandler.removeMessages(UPDATE_UI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                int progress = seekBar.getProgress();
                playerView.seekTo(progress);

                if (mCenterPlayerBtnIv.getVisibility() == GONE)
                {
                    playerView.start();
                    TimeUIHandler.sendEmptyMessage(UPDATE_UI);
                }

            }
        });

    }

    /**
     * 播放控制方法
     */
    private void playController()
    {
        if (playerView != null)
        {
            if (playerView.isPlaying())
            {
                pause();
            } else
            {
                start();
            }
        }
    }


    /**
     * 初始化播放器信息
     */
    private void initPlayerView()
    {

        try
        {
            PLNetworkManager.getInstance().startDnsCacheService(mContext);
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }

        setOptions();
        playerView.setOnErrorListener(mOnErrorListener);
        playerView.setOnCompletionListener(mOnCompletionListener);
        playerView.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer)
            {
                if (isShowPlayerBottomBar)
                {
                    mPlayerBottomBarLayout.setVisibility(VISIBLE);
                }
            }
        });
        setCoverView(coverViewDrawable);

        //是否显示加载时候的loading框
        if (isShowLoadingView)
        {
            mLoadingView.setVisibility(VISIBLE);
            playerView.setBufferingIndicator(mLoadingView);
        }


        //设置显示比例   16：9   4：3 等等
        playerView.setDisplayAspectRatio(aspectRatio);

        //支持画面的镜像变换
        playerView.setMirror(false);

        //是否重复播放
        if (repeatPlay)
        {
            playerView.setLooping(true);
        }

        setVideoPlayerPath(videoPath);
        isStartVideo(isStartVideo);


    }


    /**
     * 初始化播放封面
     */
    private VideoPlayerView setCoverView(Drawable drawable)
    {
        if (drawable != null)
        {
            mCoverView.setImageDrawable(drawable);
            playerView.setCoverView(mCoverView);
        }
        return this;
    }

    /**
     * 是否开始播放
     *
     * @param isStartVideo
     */
    private void isStartVideo(boolean isStartVideo)
    {
        if (isStartVideo && videoPath != null && !"".equals(videoPath))
        {
            playerView.start();
            TimeUIHandler.sendEmptyMessage(UPDATE_UI);
        }
    }

    /**
     * 设置视频播放地址
     *
     * @param videoPath
     */
    private void setVideoPlayerPath(String videoPath)
    {
        if (this.videoPath != null)
        {
            playerView.setVideoPath(videoPath);
        }
    }

    /**
     * 配置视频播放器器的参数
     */
    private void setOptions()
    {
        AVOptions options = new AVOptions();

        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_PROBESIZE, 128 * 1024);

        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);

        // whether start play automatically after prepared, default value is 1
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);

        playerView.setAVOptions(options);
    }

    /**
     * 播放结束回调
     */
    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer)
        {
            if (mOnCurrentProgressChange != null)
            {
                mOnCurrentProgressChange.onCurrentProgressChange(100, getTotalTime());
            }
            if (repeatPlay)
            {
                repeatPlay();
            } else
            {

                mPlayControllerIv.setImageResource(R.mipmap.default_service_video_play_xiao);
                if (isShowCenterPlayerBtn)
                {
                    mCenterPlayerBtnIv.setVisibility(VISIBLE);
                }
                TimeUIHandler.removeMessages(UPDATE_UI);

                if (mOnVideoCompletionListener != null)
                {
                    mOnVideoCompletionListener.doOnVideoCompletionListener();
                }
            }


        }
    };

    /**
     * 播放错误回调
     */
    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener()
    {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode)
        {
            switch (errorCode)
            {
                case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                    showToastTips("无效的 URL!");
                    break;
                case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                    showToastTips("播放资源不存在!");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                    showToastTips("服务器拒绝连接!");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                    showToastTips("连接超时!");
                    break;
                case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                    showToastTips("空的播放列表!");
                    break;
                case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                    showToastTips("与服务器连接断开!");
                    break;
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    showToastTips("网络异常!");
                    break;
                case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                    showToastTips("未授权，播放一个禁播的流!");
                    break;
                case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                    showToastTips("播放器准备超时!");
                    break;
                case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                    showToastTips("读取数据超时!");
                    break;
                case PLMediaPlayer.ERROR_CODE_HW_DECODE_FAILURE:
                    showToastTips("硬解失败!");
                    break;
                case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                    break;
                default:
                    showToastTips("未知错误!");
                    break;
            }

            // Return true means the error has been handled
            // If return false, then `onCompletion` will be called
            return true;
        }
    };


    /**
     * 格式化视频播放时间
     *
     * @param textView
     * @param millisecond
     */
    private void updateTextViewWithTimeFormat(TextView textView, int millisecond)
    {

        textView.setText(getFormatTime(millisecond));
    }

    /**
     * 格式化时间
     *
     * @param millisecond
     * @return
     */
    private String getFormatTime(int millisecond)
    {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;

        String str = null;
        if (hh != 0)
        {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else
        {
            str = String.format("%02d:%02d", mm, ss);
        }

        return str;
    }

    /**
     * 更新时间及播放进度UI
     */
    private Handler TimeUIHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if (msg.what == UPDATE_UI)
            {
                //获取当前播放进度和总时间
                int currentPosition = (int) playerView.getCurrentPosition();
                int totalDuration = (int) playerView.getDuration();

//                Log.d("allen", "currentPosition: " + currentPosition + "-----" + "totalDuration=" + totalDuration);
                //格式化时间SocketService
                updateTextViewWithTimeFormat(mCurrentTimeTv, currentPosition);
                updateTextViewWithTimeFormat(mTotalTimeTv, totalDuration);

                //设置进度参数
                mSeekBar.setMax(totalDuration);
                mSeekBar.setProgress(currentPosition);

                if (mOnCurrentProgressChange != null)
                {
//                    Log.e("onCurrentProgressChange", getCurrentProgress() + "");
                    mOnCurrentProgressChange.onCurrentProgressChange(getCurrentProgress(), getFormatTime(currentPosition));
                }

                //调取自己实现自动刷新
                TimeUIHandler.sendEmptyMessageDelayed(UPDATE_UI, 100);


            }
        }
    };


    private void showToastTips(String tips)
    {
        if (mToast != null)
        {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, tips, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /////////////////生命周期方法///////////////////
    public void onResume()
    {
        start();
    }

    public void onPause()
    {
        //这里不直接使用pause方法是为了解决欢迎页退出的时候不显示暂停播放的图标
        if (playerView != null)
        {
            playerView.pause();
            TimeUIHandler.removeMessages(UPDATE_UI);
            mPlayControllerIv.setImageResource(R.mipmap.default_service_video_play_xiao);
        }
    }

    public void onDestroy()
    {
        stop();
    }


    ////////////对外暴露的方法/////////////

    /**
     * 开始播放
     *
     * @return
     */
    public VideoPlayerView start()
    {
        if (playerView != null)
        {
            playerView.start();
            TimeUIHandler.sendEmptyMessage(UPDATE_UI);
            mPlayControllerIv.setImageResource(R.mipmap.default_service_video_suspend);

            if (isShowCenterPlayerBtn)
            {
                mCenterPlayerBtnIv.setVisibility(GONE);
            }
        }

        return this;
    }

    /**
     * 暂停播放
     *
     * @return
     */
    public VideoPlayerView pause()
    {
        if (playerView != null)
        {
            playerView.pause();
            TimeUIHandler.removeMessages(UPDATE_UI);
            mPlayControllerIv.setImageResource(R.mipmap.default_service_video_play_xiao);
            if (isShowCenterPlayerBtn)
            {
                mCenterPlayerBtnIv.setVisibility(VISIBLE);
            }
        }
        return this;
    }

    /**
     * 停止播放
     */
    public void stop()
    {
        if (playerView != null)
        {
            playerView.stopPlayback();
            TimeUIHandler.removeMessages(UPDATE_UI);
        }
    }

    /**
     * 循环播放
     */
    public void repeatPlay()
    {
        if (playerView != null)
        {
            playerView.setVideoPath(videoPath);
            start();
        }
    }

    /**
     * 设置视频播放地址
     *
     * @param videoPath
     * @return
     */
    public VideoPlayerView setPlayerPath(String videoPath)
    {
        if (videoPath != null)
        {
            this.videoPath = videoPath;
            playerView.setVideoPath(videoPath);
        }
        return this;
    }

    /**
     * 设置播放地址Uri
     *
     * @param videoPath
     * @return
     */
    public VideoPlayerView setPlayerUriPath(Uri videoPath)
    {
        if (videoPath != null)
        {
            playerView.setVideoURI(videoPath);
        }
        return this;
    }

    /**
     * 设置静音
     *
     * @return
     */
    public VideoPlayerView setSoundOff()
    {
        playerView.setVolume(0.0f, 0.0f);
        return this;
    }

    /**
     * 开启音量
     *
     * @return
     */
    public VideoPlayerView setSoundOpen()
    {
        playerView.setVolume(0.0f, 1f);
        return this;
    }

    /**
     * 设置音量大小
     *
     * @param sound 0.0---1.0
     * @return
     */
    public VideoPlayerView setSound(float sound)
    {
        playerView.setVolume(0.0f, sound);
        return this;
    }

    /**
     * 设置是否显示底部控制布局
     *
     * @param isShowPlayerBottomBar
     * @return
     */
    public VideoPlayerView isShowPlayerBottomBar(boolean isShowPlayerBottomBar)
    {

        this.isShowPlayerBottomBar = isShowPlayerBottomBar;

        if (isShowPlayerBottomBar)
        {
            mPlayerBottomBarLayout.setVisibility(VISIBLE);
        } else
        {
            mPlayerBottomBarLayout.setVisibility(GONE);
        }

        return this;
    }

    /**
     * 设置是否显示横屏按钮
     *
     * @param isShowFullScreenBtn
     * @return
     */
    public VideoPlayerView isShowFullScreenBtn(boolean isShowFullScreenBtn)
    {

        if (isShowFullScreenBtn)
        {
            mFullScreenBtnIv.setVisibility(VISIBLE);
        } else
        {
            mFullScreenBtnIv.setVisibility(GONE);
        }
        return this;
    }

    public VideoPlayerView isOnlyShowFullBtn(boolean isShowFullScreenBtn)
    {
        if (isShowFullScreenBtn)
        {
            mFullScreenBtnIv.setVisibility(VISIBLE);
            mPlayControllerIv.setVisibility(INVISIBLE);
            mCenterPlayerBtnIv.setVisibility(GONE);
            mCurrentTimeTv.setVisibility(INVISIBLE);
            mTotalTimeTv.setVisibility(INVISIBLE);
            mSeekBar.setVisibility(INVISIBLE);
        } else
        {
            mFullScreenBtnIv.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 设置视频显示比例
     *
     * @param screenRatio
     * @return
     */
    public VideoPlayerView setScreenRatio(int screenRatio)
    {
        playerView.setDisplayAspectRatio(screenRatio);
        return this;
    }

    /**
     * 是否显示播放loading
     *
     * @param isShowLoadingView
     * @return
     */
    public VideoPlayerView isShowLoadingView(boolean isShowLoadingView)
    {
        if (isShowLoadingView)
        {
            mLoadingView.setVisibility(VISIBLE);
        } else
        {
            mLoadingView.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 是否允许点击屏幕暂停播放
     *
     * @param isTouchForPause
     * @return
     */
    public VideoPlayerView isTouchForPause(boolean isTouchForPause)
    {
        this.isTouchForPause = isTouchForPause;
        return this;
    }

    /**
     * 是否允许循环播放
     *
     * @param repeatPlay
     * @return
     */
    public VideoPlayerView isRepeatPlay(boolean repeatPlay)
    {
        this.repeatPlay = repeatPlay;
        return this;
    }

    /**
     * 是否显示中间播放按钮   默认显示  不显示的就屏蔽触摸屏幕暂停
     *
     * @param isShowCenterPlayerBtn
     * @return
     */
    public VideoPlayerView isShowCenterPlayerBtn(boolean isShowCenterPlayerBtn)
    {
        this.isShowCenterPlayerBtn = isShowCenterPlayerBtn;
        if (!isShowCenterPlayerBtn)
        {
            mCenterPlayerBtnIv.setVisibility(GONE);
        }
        return this;
    }


    /**
     * 是否正在播放
     *
     * @return
     */
    public boolean isPlaying()
    {
        return playerView.isPlaying();
    }

    /**
     * 获取当前播放进度百分比
     *
     * @return currentProgress
     */
    public int getCurrentProgress()
    {

        int mCurrentProgress = 0;

        //获取当前播放进度和总时间
        double currentPosition = (double) playerView.getCurrentPosition();
        double totalDuration = (double) playerView.getDuration();

        if (totalDuration != -1)
        {
            mCurrentProgress = (int) (currentPosition / (totalDuration) * 100);
        }

        return mCurrentProgress;
    }

    /**
     * 获取播放总时间
     *
     * @return
     */
    public String getTotalTime()
    {
        int duration = 0;
        if (playerView != null)
        {
            duration = (int) playerView.getDuration();
        }
        return getFormatTime(duration);
    }

    /**
     * 开启全屏按钮点击事件监听
     *
     * @param activity
     * @return
     */
    public VideoPlayerView setOnFullScreenClickListener(Activity activity)
    {
        mActivity = activity;
        return this;
    }

    public VideoPlayerView setOnCurrentProgressChange(OnCurrentProgressChange mOnCurrentProgressChange)
    {
        this.mOnCurrentProgressChange = mOnCurrentProgressChange;
        return this;
    }

    public VideoPlayerView setOnVideoCompletionListener(OnVideoCompletionListener mOnVideoCompletionListener)
    {
        this.mOnVideoCompletionListener = mOnVideoCompletionListener;
        return this;
    }

    public interface OnVideoCompletionListener
    {
        void doOnVideoCompletionListener();
    }

    public interface OnCurrentProgressChange
    {
        void onCurrentProgressChange(int currentProgress, String currentTime);

    }

}
