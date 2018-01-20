package com.exsun.meizi.ui.douyu.contract;

import android.support.annotation.Keep;

import com.exsun.meizi.base.AppBaseModel;
import com.exsun.meizi.entity.douyu.RoomsEntity;
import com.exsun.meizi.entity.douyu.RoomsWithSlidersEntity;
import com.exsun.meizi.entity.douyu.SlidersEntity;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiaokun on 2017/8/28.
 */
@Keep
public interface ChannelContract
{
    interface Model extends AppBaseModel
    {
        Observable<List<SlidersEntity.DataBean>> getSlider();

        Observable<List<RoomsEntity.DataBean>> getRooms(String cateId, int limit, int offset);
    }

    interface View extends BaseView
    {
//        void getSliderSuccess(List<SlidersEntity.DataBean> sliderEntities);
//
//        void getSliderFailed(Throwable throwable);

        void getRoomsSuccess(List<RoomsEntity.DataBean> roomEntities);

        void getRoomsFailed(Throwable throwable);

        void getMixSuccess(RoomsWithSlidersEntity roomsWithSlidersEntity);

        void getMixFailed(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
//        public abstract void getSlider();

        public abstract void getRooms(String cateId, int limit, int offset);

        public abstract void getRoomsWithSliders(String cateId, int limit, int offset);
    }

}
