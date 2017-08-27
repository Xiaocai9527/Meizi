package com.exsun.meizi.ui.douyu.presenter;

import com.exsun.meizi.entity.douyu.ChannelEntity;
import com.exsun.meizi.ui.douyu.contract.DyMainContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaokun on 2017/8/25.
 */

public class DyMainPresenter extends DyMainContract.Presenter
{
    @Override
    public void getChannelsData()
    {
        mModel.getChannels().subscribe(new Observer<List<ChannelEntity>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                mRxManager.add(d);
            }

            @Override
            public void onNext(List<ChannelEntity> value)
            {
                mView.getChannelsSuccess(value);
            }

            @Override
            public void onError(Throwable e)
            {
                mView.getChannelsFailed(e);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }
}
