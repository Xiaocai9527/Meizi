package com.exsun.meizi.ui.douyu.model;

import com.exsun.meizi.entity.douyu.ChannelsEntity;
import com.exsun.meizi.ui.douyu.contract.DyMainContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class DyMainModel implements DyMainContract.Model
{
    @Override
    public Observable<List<ChannelsEntity.DataBean>> getChannels()
    {
        return dyService.getChannels().map(new Function<ChannelsEntity, List<ChannelsEntity.DataBean>>()
        {
            @Override
            public List<ChannelsEntity.DataBean> apply(ChannelsEntity channelsEntity) throws Exception
            {
                return channelsEntity.getData();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
