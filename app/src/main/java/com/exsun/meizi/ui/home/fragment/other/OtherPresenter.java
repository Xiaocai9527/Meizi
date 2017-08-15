package com.exsun.meizi.ui.home.fragment.other;

import com.exsun.meizi.entity.AndroidMixEntity;
import com.exsun.meizi.entity.GankCategoryEntity;
import com.exsun.meizi.entity.RadomMzEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/4.
 */

public class OtherPresenter extends OtherContract.Presenter
{
    @Override
    public void getAndroidMix(String category1, String category2, int count, int page)
    {
        Observable.zip(mModel.getCategoryData(category2, count, page), mModel.getRadomMz(category1, page),
                new BiFunction<List<GankCategoryEntity.ResultsBean>, RadomMzEntity, AndroidMixEntity>()
                {
                    @Override
                    public AndroidMixEntity apply(List<GankCategoryEntity.ResultsBean> resultsBeen, RadomMzEntity radomMzEntity) throws Exception
                    {
                        AndroidMixEntity androidMixEntity = new AndroidMixEntity();
                        androidMixEntity.setResults(resultsBeen);
                        androidMixEntity.setUrl(radomMzEntity.getResults().get(0).getUrl());
                        return androidMixEntity;
                    }
                }).observeOn(Schedulers.io())
                .doOnNext(new Consumer<AndroidMixEntity>()
                {
                    @Override
                    public void accept(AndroidMixEntity androidMixEntity) throws Exception
                    {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AndroidMixEntity>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        mRxManager.add(d);
                    }

                    @Override
                    public void onNext(AndroidMixEntity value)
                    {
                        mView.getAndroidMixSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mView.getAndroidMixFailed(e);
                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });
    }
}
