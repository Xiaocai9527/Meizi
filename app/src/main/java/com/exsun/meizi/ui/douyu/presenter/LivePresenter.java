package com.exsun.meizi.ui.douyu.presenter;

import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.entity.douyu.RoomInfoEntity2;
import com.exsun.meizi.ui.douyu.contract.LiveContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaokun on 2017/8/29.
 */

public class LivePresenter extends LiveContract.Presenter
{
    @Override
    public void getCDNandRateInfo(String roomId)
    {
        mModel.getCDNandRateInfo(roomId).subscribe(new Observer<RoomInfoEntity.DataBean>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(RoomInfoEntity.DataBean value)
            {
                RoomInfoEntity.DataBean.CdnsWithNameBean tempCdn = new RoomInfoEntity.DataBean.CdnsWithNameBean();
                tempCdn.setName("临时线路");
                tempCdn.setCdn("temp");
                value.getCdnsWithName().add(tempCdn);
                mView.updateCDNandRateInfo(value.getCdnsWithName(), value.getMultirates());
                restoreCDN(value.getCdnsWithName());
                restoreRate(value.getMultirates());
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    @Override
    public void getHLSUrl(String roomid, String cdn, final String rate)
    {
        mModel.getHLSUrl(roomid, cdn, rate).subscribe(new Observer<RoomInfoEntity2.DataBean>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(RoomInfoEntity2.DataBean value)
            {
                String url = "";
                String str = rate;
                int obj = -1;
                switch (str.hashCode())
                {
                    case 48:
                        if (str.equals("0"))
                        {
                            obj = 0;
                        }
                        break;
                    case 49:
                        if (str.equals("1"))
                        {
                            obj = 1;
                        }
                        break;
                    case 50:
                        if (str.equals("2"))
                        {
                            obj = 2;
                        }
                        break;
                    default:

                        break;
                }

                switch (obj)
                {
                    case 0:
                        url = value.getRtmp_url() + "/" + value.getRtmp_live();
                        break;
                    case 1:
                        url = value.getRtmp_url() + "/" + value.getRtmp_multi_bitrate().getMiddle();
                        break;
                    case 2:
                        url = value.getRtmp_url() + "/" + value.getRtmp_multi_bitrate().getMiddle2();
                        break;
                    default:
                        url = value.getRtmp_url() + "/" + value.getRtmp_live();
                        break;
                }
                mView.updateHLSUrl(url);
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    /**
     * 保存cdn，线路如主线路、临时线路
     *
     * @param cdnsWithName
     */
    public void restoreCDN(List<RoomInfoEntity.DataBean.CdnsWithNameBean> cdnsWithName)
    {
        String cdnCode = MzApplication.mPref.get(Constant.CDN_CODE, "ws");
        for (RoomInfoEntity.DataBean.CdnsWithNameBean cdn : cdnsWithName)
        {
            if (cdn.getCdn().equals(cdnCode))
            {
                mView.upDateCDN(cdn);
                return;
            }
        }

        MzApplication.mPref.put(Constant.CDN_CODE, cdnsWithName.get(0).getCdn());
        mView.upDateCDN(cdnsWithName.get(0));
    }

    /**
     * 保存费率，如超清、高清、普清
     *
     * @param multirates
     */
    public void restoreRate(List<RoomInfoEntity.DataBean.MultiratesBean> multirates)
    {
        int rateCode = Integer.valueOf(MzApplication.mPref.get(Constant.RATE_CODE, "0")).intValue();
        for (RoomInfoEntity.DataBean.MultiratesBean rate : multirates)
        {
            if (rate.getType() == rateCode)
            {
                mView.upDateRate(rate);
                return;
            }
        }

        mView.upDateRate(multirates.get(0));
    }

    /**
     * 保存设置
     */
    public void restoreSetting()
    {
        mView.setMediaCodec(MzApplication.mPref.get(Constant.USING_MEDIA_CODEC, false));
    }

    /**
     * 线路改变
     *
     * @param nameBean
     */
    public void onCDNChange(RoomInfoEntity.DataBean.CdnsWithNameBean nameBean)
    {
        //如果当前选择的线路跟保存在sp中的不一致，表示选择了不同线路
        if (!nameBean.getCdn().equals(MzApplication.mPref.get(Constant.CDN_CODE, "ws")))
        {
            mView.upDateCDN(nameBean);
//            mView.preparePlay();
        }
    }

    /**
     * 清晰度改变
     *
     * @param rate
     */
    public void onRateChange(RoomInfoEntity.DataBean.MultiratesBean rate)
    {
        mView.upDateRate(rate);
//        mView.preparePlay();
    }
}
