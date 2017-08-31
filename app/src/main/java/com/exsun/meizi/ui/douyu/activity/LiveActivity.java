package com.exsun.meizi.ui.douyu.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;

import com.allen.playerview.VideoPlayerView;
import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.ui.douyu.contract.LiveContract;
import com.exsun.meizi.ui.douyu.model.LiveModel;
import com.exsun.meizi.ui.douyu.presenter.LivePresenter;
import com.yuyh.library.Base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class LiveActivity extends BaseActivity<LivePresenter, LiveModel> implements LiveContract.View
{
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_TITLE = "room_title";
    @Bind(R.id.player_view)
    VideoPlayerView mPlayerView;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.cdn)
    Button cdn;
    @Bind(R.id.rate)
    Button rate;
    private String roomId;
    private RoomInfoEntity.DataBean.CdnsWithNameBean mCDN;
    private RoomInfoEntity.DataBean.MultiratesBean mRate;
    private PopupMenu cdnPop;
    private PopupMenu ratePop;
    private String roomTitle;

    @Override
    public void initData(Bundle bundle)
    {
        if (bundle == null)
        {
            return;
        }
        roomId = bundle.getString(ROOM_ID, "");
        roomTitle = bundle.getString(ROOM_TITLE, "");
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_live;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    public void doBeforeSetcontentView()
    {
        super.doBeforeSetcontentView();
        /*set it to be full screen*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 防止锁屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void initView()
    {
        mPlayerView.setOnFullScreenClickListener(this);
        mPlayerView.isShowPlayerBottomBar(true);
//        mPlayerView.isShowFullScreenBtn(true);
        mPlayerView.isOnlyShowFullBtn(true);
        mCDN = new RoomInfoEntity.DataBean.CdnsWithNameBean();
        mRate = new RoomInfoEntity.DataBean.MultiratesBean();
        mCDN.setCdn("ws");
        mCDN.setName("主线路");
        mRate.setName("超清");
        mRate.setType(0);
    }

    @Override
    public void doBusiness(Context context)
    {
        mPresenter.restoreSetting();
        mPresenter.getCDNandRateInfo(roomId);
    }

    @OnClick({R.id.cdn, R.id.rate, R.id.button})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.cdn:
                if (cdnPop != null)
                {
                    cdnPop.show();
                }
                break;
            case R.id.rate:
                if (ratePop != null)
                {
                    ratePop.show();
                }
                break;
            case R.id.button:
                break;
        }
    }

    @Override
    public void setMediaCodec(boolean b)
    {
        preparePlay();
    }

    @Override
    public void updateHLSUrl(String url)
    {
        mPlayerView.setPlayerPath(url).start();
    }

    @Override
    public void updateCDNandRateInfo(final List<RoomInfoEntity.DataBean.CdnsWithNameBean> list, final List<RoomInfoEntity.DataBean.MultiratesBean> list2)
    {
        cdnPop = new PopupMenu(this, cdn);
        for (RoomInfoEntity.DataBean.CdnsWithNameBean nameBean : list)
        {
            cdnPop.getMenu().add(nameBean.getName());
        }
        cdnPop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                for (RoomInfoEntity.DataBean.CdnsWithNameBean nameBean : list)
                {
                    if (nameBean.getName().equals(item.getTitle()))
                    {
                        mPresenter.onCDNChange(nameBean);
                    }
                }
                return false;
            }
        });

        ratePop = new PopupMenu(this, rate);
        for (RoomInfoEntity.DataBean.MultiratesBean rate : list2)
        {
            ratePop.getMenu().add(rate.getName());
        }
        ratePop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                for (RoomInfoEntity.DataBean.MultiratesBean rate : list2)
                {
                    if (rate.getName().equals(item.getTitle()))
                    {
                        mPresenter.onRateChange(rate);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    /**
     * 准备播放
     */
    @Override
    public void preparePlay()
    {
        if (!TextUtils.isEmpty(roomId) && mCDN != null && mRate != null)
        {
            mPresenter.getHLSUrl(roomId, mCDN.getCdn(), mRate.getType() + "");
        }
    }

    @Override
    public void upDateCDN(RoomInfoEntity.DataBean.CdnsWithNameBean cdnsWithNameBean)
    {
        this.mCDN = cdnsWithNameBean;
        cdn.setText(cdnsWithNameBean.getName());
        preparePlay();
    }

    @Override
    public void upDateRate(RoomInfoEntity.DataBean.MultiratesBean multiratesBean)
    {
        this.mRate = multiratesBean;
        rate.setText(multiratesBean.getName());
        preparePlay();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mPlayerView != null)
        {
            mPlayerView.onDestroy();
        }
    }
}
