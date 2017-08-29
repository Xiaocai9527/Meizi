package com.exsun.meizi.ui.douyu.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.PopupMenu;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.ui.douyu.contract.LiveContract;
import com.exsun.meizi.ui.douyu.model.LiveModel;
import com.exsun.meizi.ui.douyu.presenter.LivePresenter;
import com.exsun.meizi.widget.media.IMediaController;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;
import com.yuyh.library.Base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class LiveActivity extends BaseActivity<LivePresenter, LiveModel> implements IMediaController, LiveContract.View
{
    public static final String ROOM_ID = "room_id";
    public static final String ROOM_TITLE = "room_title";
    @Bind(R.id.player_view)
    NiceVideoPlayer mPlayerView;
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

    @Override
    public void initView()
    {
        mPlayerView.setPlayerType(NiceVideoPlayer.TYPE_IJK);
//        initIjkPlayer();
    }

    private void initIjkPlayer()
    {
//        IjkMediaPlayer.loadLibrariesOnce(null);
//        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
//        videoView.setMediaController(this);
//        videoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener()
//        {
//            @Override
//            public void onPrepared(IMediaPlayer iMediaPlayer)
//            {
//
//            }
//        });
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
//        if (mPlayerView != null)
//        {
//            Setting.setUsingMediaCodec(b);
//            Setting.setMediaCodecHandleResolutionChange(b);
//            if (mPlayerView.isPlaying())
//            {
//                preparePlay();
//            }
//        }
    }

    @Override
    public void updateHLSUrl(String url)
    {
        mPlayerView.setUp(url, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle(roomTitle);
        mPlayerView.setController(controller);
//        mPlayerView.init()
//                .setTitle("hahaha")
////                .setSkipTip(1000 * 60 * 1)
//                .enableOrientation()
//                .setVideoPath(url)
//                .start();
//        videoView.setVideoURI(Uri.parse(url));
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

    /**
     * zhunbei bofang
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
    }

    @Override
    public void upDateRate(RoomInfoEntity.DataBean.MultiratesBean multiratesBean)
    {
        this.mRate = multiratesBean;
        rate.setText(multiratesBean.getName());
    }


    @Override
    public void hide()
    {

    }

    @Override
    public boolean isShowing()
    {
        return false;
    }

    @Override
    public void setAnchorView(View view)
    {

    }

    @Override
    public void setEnabled(boolean z)
    {

    }

    @Override
    public void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl)
    {

    }

    @Override
    public void show()
    {

    }

    @Override
    public void show(int i)
    {

    }

    @Override
    public void showOnce(View view)
    {

    }
}
