package com.exsun.meizi.ui.douyu.presenter;

import com.exsun.meizi.entity.douyu.RoomsEntity;
import com.exsun.meizi.entity.douyu.RoomsWithSlidersEntity;
import com.exsun.meizi.entity.douyu.SlidersEntity;
import com.exsun.meizi.ui.douyu.contract.ChannelContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class ChannelPresenter extends ChannelContract.Presenter
{
//    @Override
//    public void getSlider()
//    {
//        mModel.getSlider().subscribe(new Observer<List<SlidersEntity.DataBean>>()
//        {
//            @Override
//            public void onSubscribe(Disposable d)
//            {
//                mRxManager.add(d);
//            }
//
//            @Override
//            public void onNext(List<SlidersEntity.DataBean> value)
//            {
//                mView.getSliderSuccess(value);
//            }
//
//            @Override
//            public void onError(Throwable e)
//            {
//                mView.getSliderFailed(e);
//            }
//
//            @Override
//            public void onComplete()
//            {
//
//            }
//        });
//    }

    @Override
    public void getRooms(String cateId, int limit, int offset)
    {
        mModel.getRooms(cateId, limit, offset).subscribe(new Observer<List<RoomsEntity.DataBean>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                mRxManager.add(d);
            }

            @Override
            public void onNext(List<RoomsEntity.DataBean> value)
            {
                mView.getRoomsSuccess(value);
            }

            @Override
            public void onError(Throwable e)
            {
                mView.getRoomsFailed(e);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    @Override
    public void getRoomsWithSliders(String cateId, int limit, int offset)
    {
        Observable.zip(mModel.getSlider(), mModel.getRooms(cateId, limit, offset),
                new BiFunction<List<SlidersEntity.DataBean>, List<RoomsEntity.DataBean>, RoomsWithSlidersEntity>()
                {
                    @Override
                    public RoomsWithSlidersEntity apply(List<SlidersEntity.DataBean> dataBeen, List<RoomsEntity.DataBean> dataBeen2) throws Exception
                    {
                        RoomsWithSlidersEntity roomsWithSlidersEntity = new RoomsWithSlidersEntity();
                        roomsWithSlidersEntity.setRoomData(dataBeen2);
                        roomsWithSlidersEntity.setSliderData(dataBeen);
                        return roomsWithSlidersEntity;
                    }
                }).subscribe(new Observer<RoomsWithSlidersEntity>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                mRxManager.add(d);
            }

            @Override
            public void onNext(RoomsWithSlidersEntity value)
            {
                mView.getMixSuccess(value);
            }

            @Override
            public void onError(Throwable e)
            {
                mView.getMixFailed(e);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }
}
