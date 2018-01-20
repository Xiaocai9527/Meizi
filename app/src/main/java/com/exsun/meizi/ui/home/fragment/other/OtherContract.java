package com.exsun.meizi.ui.home.fragment.other;

import android.support.annotation.Keep;

import com.exsun.meizi.base.AppBaseModel;
import com.exsun.meizi.entity.gank.AndroidMixEntity;
import com.exsun.meizi.entity.gank.GankCategoryEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiaokun on 2017/8/4.
 */
@Keep
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
