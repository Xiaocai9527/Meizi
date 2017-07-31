package com.exsun.meizi.ui.fragment.home;

import android.util.Log;

import com.exsun.meizi.entity.GankCategoryEntity;
import com.exsun.meizi.entity.HomeMixEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class HomePresenter extends HomeContract.Presenter
{
    @Override
    public void getMixData(String welfare, String android, int count, int page)
    {
        Observable.zip(mModel.getCategoryData(welfare, count, page), mModel.getCategoryData(android, count, page),
                new BiFunction<List<GankCategoryEntity.ResultsBean>, List<GankCategoryEntity.ResultsBean>, List<HomeMixEntity>>()
                {
                    @Override
                    public List<HomeMixEntity> apply(List<GankCategoryEntity.ResultsBean> welfares, List<GankCategoryEntity.ResultsBean> androids) throws Exception
                    {
                        List<HomeMixEntity> datas = new ArrayList<HomeMixEntity>();
                        int min = Math.min(welfares.size(), androids.size());
                        for (int i = 0; i < min; i++)
                        {
                            HomeMixEntity homeMixEntity = new HomeMixEntity();
                            homeMixEntity.setDesc(androids.get(i).getDesc());
                            homeMixEntity.setDate(welfares.get(i).getDesc());
                            homeMixEntity.setUrl(welfares.get(i).getUrl());
                            datas.add(homeMixEntity);
                        }
                        return datas;
                    }
                }).observeOn(Schedulers.io())
                .doOnNext(new Consumer<List<HomeMixEntity>>()
                {
                    @Override
                    public void accept(List<HomeMixEntity> homeMixEntities) throws Exception
                    {
                        mModel.saveMeizhis(homeMixEntities);
                        Log.e("HomePresenter", "call(HomePresenter.java:76)" + "current thread: " + Thread.currentThread().getName());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HomeMixEntity>>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(List<HomeMixEntity> homeMixEntities)
                    {
                        Log.e("HomePresenter", "onNext(HomePresenter.java:101)" + "onNext");
                        if (homeMixEntities == null)
                        {
                            return;
                        }
                        mView.getMixSuccess(homeMixEntities);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mView.getCategoryFailed(e);
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
    }

    @Override
    public void getMixDataFormDB()
    {
        mModel.getCategoryDataFromDB().subscribe(new Observer<List<HomeMixEntity>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(List<HomeMixEntity> value)
            {
                mView.getDataFromDBSuccess(value);
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

}
