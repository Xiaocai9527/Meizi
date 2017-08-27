package com.exsun.meizi.ui.douyu.model;

import com.exsun.meizi.entity.douyu.ChannelEntity;
import com.exsun.meizi.ui.douyu.contract.DyMainContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class DyMainModel implements DyMainContract.Model
{
    @Override
    public Observable<List<ChannelEntity>> getChannels()
    {
        return dyService.getChannels().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
