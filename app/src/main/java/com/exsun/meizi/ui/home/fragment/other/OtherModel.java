package com.exsun.meizi.ui.home.fragment.other;

import android.support.annotation.Keep;

import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/4.
 */
@Keep
public class OtherModel implements OtherContract.Model
{
    @Override
    public Observable<List<GankCategoryEntity.ResultsBean>> getCategoryData(String category, int count, int page)
    {
        return apiService.getCategoryData(category, count, page)
                .map(new Function<GankCategoryEntity, List<GankCategoryEntity.ResultsBean>>()
                {
                    @Override
                    public List<GankCategoryEntity.ResultsBean> apply(GankCategoryEntity gankCategoryEntity) throws Exception
                    {
                        return gankCategoryEntity.getResults();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RadomMzEntity> getRadomMz(String category, int page)
    {
        return apiService.getRadomMz(category, page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
