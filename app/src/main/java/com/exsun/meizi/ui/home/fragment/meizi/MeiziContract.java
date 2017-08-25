package com.exsun.meizi.ui.home.fragment.meizi;

import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.HomeMixEntity;
import com.exsun.meizi.base.AppBaseModel;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by xiaokun on 2017/7/26.
 */

public interface MeiziContract
{
    interface Model extends AppBaseModel
    {
        Observable<List<GankCategoryEntity.ResultsBean>> getCategoryData(String category, int count, int page);

        void saveMeizhis(List<HomeMixEntity> homeMixEntities);

        Observable<List<HomeMixEntity>> getCategoryDataFromDB();
    }

    interface View extends BaseView
    {
//        void getCategorySuccess(List<GankCategoryEntity.ResultsBean> resultsBeanList);

        void getDataFromDBSuccess(List<HomeMixEntity> homeMixEntities);

        void getMixSuccess(List<HomeMixEntity> homeMixEntities);

//        void getCategoryFailed(Throwable e);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
//        public abstract void getCategory(String category, int count, int page);

        public abstract void getMixData(String category1, String category2, int count, int page);

        public abstract void getMixDataFormDB();
    }

}
