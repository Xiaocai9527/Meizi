package com.exsun.meizi.ui.douyu.contract;

import android.support.annotation.Keep;

import com.exsun.meizi.base.AppBaseModel;
import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.entity.douyu.RoomInfoEntity2;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiaokun on 2017/8/29.
 */
@Keep
public interface LiveContract
{
    interface Model extends AppBaseModel
    {
        Observable<RoomInfoEntity.DataBean> getCDNandRateInfo(String roomId);

        Observable<RoomInfoEntity2.DataBean> getHLSUrl(String roomid, String cdn, String rate);
    }

    interface View extends BaseView
    {
        /**
         * 更新线路和清晰度信息
         *
         * @param list
         * @param list2
         */
        void updateCDNandRateInfo(List<RoomInfoEntity.DataBean.CdnsWithNameBean> list,
                                  List<RoomInfoEntity.DataBean.MultiratesBean> list2);

        /**
         * 准备播放
         */
        void preparePlay();

        /**
         * 更新线路
         *
         * @param cdnsWithNameBean
         */
        void upDateCDN(RoomInfoEntity.DataBean.CdnsWithNameBean cdnsWithNameBean);

        /**
         * 更新清晰度
         *
         * @param multiratesBean
         */
        void upDateRate(RoomInfoEntity.DataBean.MultiratesBean multiratesBean);

        void setMediaCodec(boolean b);

        void updateHLSUrl(String url);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        public abstract void getCDNandRateInfo(String roomId);

        public abstract void getHLSUrl(String roomid, String cdn, String rate);
    }

}
