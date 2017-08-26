package com.exsun.meizi.network;

import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.GankDayEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;
import com.exsun.meizi.entity.gank.SearchEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xiaokun on 2017/7/26.
 */

public interface ApiService
{
    @GET("data/{category}/{count}/{page}")
    Observable<GankCategoryEntity> getCategoryData(@Path("category") String category,
                                                   @Path("count") int count, @Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankDayEntity> getDayData(@Path("year") int year, @Path("month") int month
            , @Path("day") int day);

    @GET("random/data/{category}/{page}")
    Observable<RadomMzEntity> getRadomMz(@Path("category") String category, @Path("page") int page);

    @GET("search/query/{queryWhat}/category/{category}/count/{count}/page/{page}")
    Observable<SearchEntity> getSearchData(@Path("queryWhat") String queryWhat, @Path("category") String category
            , @Path("count") int count, @Path("page") int page);
}
