package com.exsun.meizi.ui.home.fragment.meizi;

import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.HomeMixEntity;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.exsun.meizi.base.MzApplication.cache;


/**
 * Created by xiaokun on 2017/7/26.
 */

public class MeiziModel implements MeiziContract.Model
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
    public void saveMeizhis(List<HomeMixEntity> homeMixEntities)
    {
        cache.put(Constant.TEN_MEIZI, (Serializable) homeMixEntities);
        MzApplication.sDb.insert(homeMixEntities, ConflictAlgorithm.Replace);
    }

    @Override
    public Observable<List<HomeMixEntity>> getCategoryDataFromDB()
    {
        return Observable.create(new ObservableOnSubscribe<List<HomeMixEntity>>()
        {
            @Override
            public void subscribe(ObservableEmitter<List<HomeMixEntity>> e) throws Exception
            {
//                QueryBuilder query = new QueryBuilder(HomeMixEntity.class);
//                query.limit(0, 10);
//                ArrayList<HomeMixEntity> list = MzApplication.sDb.query(query);
                List<HomeMixEntity> list = (List<HomeMixEntity>) MzApplication.cache.getAsObject(Constant.TEN_MEIZI);
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
