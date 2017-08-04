package com.exsun.meizi.ui.fragment.other;

import com.exsun.meizi.entity.AndroidMixEntity;
import com.exsun.meizi.entity.GankCategoryEntity;
import com.exsun.meizi.entity.RadomMzEntity;
import com.exsun.meizi.ui.base.AppBaseModel;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiaokun on 2017/8/4.
 */

public interface OtherContract
{
    interface Model extends AppBaseModel
    {
        Observable<List<GankCategoryEntity.ResultsBean>> getCategoryData(String category, int count, int page);

        Observable<RadomMzEntity> getRadomMz(String category, int page);
    }

    interface View extends BaseView
    {
        void getAndroidMixSuccess(AndroidMixEntity androidMixEntity);

        void getAndroidMixFailed(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        public abstract void getAndroidMix(String category1, String category2, int count, int page);

//        public abstract void getCategory(String category, int count, int page);
//
//        public abstract void getRadomImage(String category, int page);
    }
}
