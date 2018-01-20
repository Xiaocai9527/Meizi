package com.exsun.meizi.ui.douyu.contract;

import com.exsun.meizi.base.AppBaseModel;
import com.exsun.meizi.entity.douyu.ChannelsEntity;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiaokun on 2017/8/25.
 */
public interface DyMainContract
{
    interface Model extends AppBaseModel
    {
        Observable<List<ChannelsEntity.DataBean>> getChannels();
    }

    interface View extends BaseView
    {
        void getChannelsSuccess(List<ChannelsEntity.DataBean> channelEntities);

        void getChannelsFailed(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        public abstract void getChannelsData();
    }
}
