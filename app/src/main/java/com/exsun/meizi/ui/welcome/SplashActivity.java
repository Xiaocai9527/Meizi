package com.exsun.meizi.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.eftimoff.androipathview.PathView;
import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.ui.home.activity.HomeActivity;
import com.exsun.meizi.widget.FadeInTextView;
import com.exsun.meizi.widget.GraduallyTextView;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.utils.DimenUtils;

import java.util.Random;

import butterknife.Bind;

import static com.exsun.meizi.R.array.imgs;

public class SplashActivity extends BaseActivity
{
    @Nullable
    @Bind(R.id.activity_splash)
    LinearLayout activitySplash;
    @Bind(R.id.pathView)
    PathView pathView;
    //    @Bind(R.id.textview)
//    TextView textview;
    @Bind(R.id.textview)
    FadeInTextView textview;

    private int mDelayTime = 1600;
    private char[] charArrays;
    private String len = "";
    private int position = 0;

    private Runnable mGotoMainRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            Intent localIntent = new Intent(SplashActivity.this, HomeActivity.class);
            SplashActivity.this.startActivity(localIntent);
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            SplashActivity.this.finish();
        }
    };

    @Override
    protected void onPause()
    {
        super.onPause();
        getWindow().getDecorView().removeCallbacks(this.mGotoMainRunnable);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
//        getWindow().getDecorView().postDelayed(this.mGotoMainRunnable, this.mDelayTime);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        if (MzApplication.mPref.get(Constant.DAY_NIGHT_STYLE, false))
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_splash;
    }

    @Override
    protected void initPresenter()
    {

    }


    @Override
    public void initView()
    {
        if (activitySplash != null)
        {
            TypedArray typedArray = getResources().obtainTypedArray(imgs);
            int randomImg = randomInt(typedArray.length() - 1, 0);
            int length = typedArray.length();
            int imgs[] = new int[length];
            for (int i = 0; i < length; i++)
            {
                imgs[i] = typedArray.getResourceId(i, 0);
            }
            activitySplash.setBackgroundResource(imgs[randomImg]);
        }
        pathView.setPathWidth(DimenUtils.dpToPx(4));
        pathView.setPathColor(Color.parseColor("#323232"));

        String[] rhesis = getResources().getStringArray(R.array.rhesis);
        int randomNumber = randomInt(rhesis.length - 1, 0);
        final String randomStr = rhesis[randomNumber];
//      另一种textview逐字打印效果实现
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    charArrays = randomStr.toCharArray();
//                    for (int i = 0; i < charArrays.length; i++)
//                    {
//                        position = i;
//                        sleep(200);
//                        len = charArrays[i] + "";
//                        handler.sendEmptyMessage(0);
//                    }
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        textview.setTextString("\u3000\u3000" + randomStr)
                .startFadeInAnimation()
                .setTextAnimationListener(new FadeInTextView.TextAnimationListener()
                {
                    @Override
                    public void animationFinish()
                    {
                        pathView.getPathAnimator().delay(100).duration(1500).interpolator(new AccelerateDecelerateInterpolator())
                                .listenerEnd(listenerEnd).start();
                    }
                });
    }

    /**
     * 区间随机数
     *
     * @param max
     * @param min
     * @return
     */
    private int randomInt(int max, int min)
    {
        // For Java
        //int randNumber = rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    textview.append(len);
                    if (position == (charArrays.length - 1))
                    {
                        pathView.getPathAnimator().delay(100).duration(1500).interpolator(new AccelerateDecelerateInterpolator())
                                .listenerEnd(listenerEnd).start();
                    }
                    break;

                default:
                    break;
            }
        }

    };

    PathView.AnimatorBuilder.ListenerEnd listenerEnd = new PathView.AnimatorBuilder.ListenerEnd()
    {
        @Override
        public void onAnimationEnd()
        {
            Intent localIntent = new Intent(SplashActivity.this, HomeActivity.class);
            SplashActivity.this.startActivity(localIntent);
            overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            SplashActivity.this.finish();
        }
    };

    GraduallyTextView.OnceAnimListener onceAnimListener = new GraduallyTextView.OnceAnimListener()
    {
        @Override
        public void onlyOnceStop()
        {
//            textview.stopLoading();
            pathView.getPathAnimator().delay(100).duration(1500).interpolator(new AccelerateDecelerateInterpolator())
                    .listenerEnd(listenerEnd).start();
        }
    };

    @Override
    public void doBusiness(Context context)
    {

    }

    @Override
    public void onBackPressed()
    {
    }
}
