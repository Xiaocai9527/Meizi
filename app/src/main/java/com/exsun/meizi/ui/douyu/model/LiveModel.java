package com.exsun.meizi.ui.douyu.model;

import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.entity.douyu.RoomInfoEntity2;
import com.exsun.meizi.ui.douyu.contract.LiveContract;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/29.
 */

public class LiveModel implements LiveContract.Model
{
    @Override
    public Observable<RoomInfoEntity.DataBean> getCDNandRateInfo(String roomId)
    {
        String time = String.valueOf((new Date().getTime() / 1000) / 60);
        String signContent = roomId + "bLFlashflowlad92" + time;
        String sign = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signContent.getBytes("utf-8"));
            sign = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e)
        {
        }
        return dyService.getRoomInfo(roomId, "", "yes", time, sign).retry(3)
                .map(new Function<RoomInfoEntity, RoomInfoEntity.DataBean>()
                {
                    @Override
                    public RoomInfoEntity.DataBean apply(RoomInfoEntity roomInfoEntity) throws Exception
                    {
                        return roomInfoEntity.getData();
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RoomInfoEntity2.DataBean> getHLSUrl(String roomid, String cdn, String rate)
    {
        if (cdn.equals("temp"))
        {
            return null;
//            return dyService.getTempLiveInfo(roomid).retry(3);
        }
        String time = String.valueOf(new Date().getTime() / 1000);
        String aid = "androidhd1";
        String client_sys = "android";
        String authStr = "room/" + roomid + "?aid=androidhd1&cdn=" + cdn + "&client_sys=android&time=" + time + "Y237pxTx2In5ayGz";
        String authMD5 = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(authStr.getBytes());
            authMD5 = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e)
        {
        }

        return dyService.getLiveInfo(roomid, aid, cdn, client_sys, time, authMD5)
                .retry(3)
                .map(new Function<RoomInfoEntity2, RoomInfoEntity2.DataBean>()
                {
                    @Override
                    public RoomInfoEntity2.DataBean apply(RoomInfoEntity2 roomInfoEntity2) throws Exception
                    {
                        return roomInfoEntity2.getData();
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
