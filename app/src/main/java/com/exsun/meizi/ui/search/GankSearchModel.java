package com.exsun.meizi.ui.search;

import com.exsun.meizi.entity.gank.SearchEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/12.
 */

public class GankSearchModel implements GankSearchContract.Model
{
    @Override
    public Observable<List<SearchEntity.ResultsBean>> getSearchEntity(String queryWhat, String type, int count, int page)
    {
        return apiService.getSearchData(queryWhat, type, count, page)
                .map(new Function<SearchEntity, List<SearchEntity.ResultsBean>>()
                {
                    @Override
                    public List<SearchEntity.ResultsBean> apply(SearchEntity searchEntity) throws Exception
                    {
                        return searchEntity.getResults();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
