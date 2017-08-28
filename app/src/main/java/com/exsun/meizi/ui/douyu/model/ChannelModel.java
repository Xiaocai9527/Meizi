package com.exsun.meizi.ui.douyu.model;

import com.exsun.meizi.entity.douyu.RoomsEntity;
import com.exsun.meizi.entity.douyu.SlidersEntity;
import com.exsun.meizi.ui.douyu.contract.ChannelContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class ChannelModel implements ChannelContract.Model
{
    @Override
    public Observable<List<SlidersEntity.DataBean>> getSlider()
    {
        return dyService.getSliders()
                .map(new Function<SlidersEntity, List<SlidersEntity.DataBean>>()
                {
                    @Override
                    public List<SlidersEntity.DataBean> apply(SlidersEntity slidersEntity) throws Exception
                    {
                        return slidersEntity.getData();
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<RoomsEntity.DataBean>> getRooms(String cateId, int limit, int offset)
    {
        return dyService.getRooms(cateId, limit, offset)
                .map(new Function<RoomsEntity, List<RoomsEntity.DataBean>>()
                {
                    @Override
                    public List<RoomsEntity.DataBean> apply(RoomsEntity roomsEntity) throws Exception
                    {
                        return roomsEntity.getData();
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
