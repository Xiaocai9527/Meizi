package com.exsun.meizi.ui.search;

import com.exsun.meizi.entity.gank.SearchEntity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/12.
 */

public class GankSearchPresenter extends GankSearchContract.Presenter
{
    @Override
    public void getSearchData(String queryWhat, String type, int count, int page)
    {
        mModel.getSearchEntity(queryWhat, type, count, page).subscribe(new Observer<List<SearchEntity.ResultsBean>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                mRxManager.add(d);
            }

            @Override
            public void onNext(List<SearchEntity.ResultsBean> value)
            {
                mView.getSearchSuccess(value);
            }

            @Override
            public void onError(Throwable e)
            {
                mView.getSearchFailed(e);
            }

            @Override
            public void onComplete()
            {

            }
        });
    }
}
