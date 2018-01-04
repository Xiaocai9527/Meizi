package com.exsun.meizi.ui.home.fragment.meizi;

import android.support.annotation.Keep;

import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.HomeMixEntity;
import com.yuyh.library.Base.util.RxTransUtil;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

import static com.exsun.meizi.base.MzApplication.cache;


/**
 * Created by xiaokun on 2017/7/26.
 */
@Keep
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
                })
                .compose(RxTransUtil.<List<GankCategoryEntity.ResultsBean>>rxSchedulerHelper());
    }

    @Override
    public void saveMeizhis(List<HomeMixEntity> homeMixEntities, boolean isSaveToDataBase)
    {
//        cache.put(Constant.TEN_MEIZI, (Serializable) homeMixEntities);
//        MzApplication.sDb.insert(homeMixEntities, ConflictAlgorithm.Replace);
        if (isSaveToDataBase)
        {
            cache.put(Constant.TEN_MEIZI, (Serializable) homeMixEntities);
//            MzApplication.sDb.deleteAll(HomeMixEntity.class);
//            MzApplication.sDb.save(homeMixEntities);
        }
    }

    @Override
    public Observable<List<HomeMixEntity>> getCategoryDataFromDB()
    {
        return Observable.create(new ObservableOnSubscribe<List<HomeMixEntity>>()
        {
            @Override
            public void subscribe(ObservableEmitter<List<HomeMixEntity>> e) throws Exception
            {
                List<HomeMixEntity> list = (List<HomeMixEntity>) MzApplication.cache.getAsObject(Constant.TEN_MEIZI);
//                QueryBuilder query = new QueryBuilder(HomeMixEntity.class);
////                query.appendOrderDescBy("date");
//                query.limit(0, 10);
//                List<HomeMixEntity> list = MzApplication.sDb.query(query);
////                List<HomeMixEntity> list = (List<HomeMixEntity>) MzApplication.cache.getAsObject(Constant.TEN_MEIZI);
                e.onNext(list);
            }
        }).compose(RxTransUtil.<List<HomeMixEntity>>rxSchedulerHelper());
    }
}
