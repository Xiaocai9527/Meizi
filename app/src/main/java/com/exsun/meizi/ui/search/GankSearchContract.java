package com.exsun.meizi.ui.search;

import com.exsun.meizi.base.AppBaseModel;
import com.exsun.meizi.entity.SearchEntity;
import com.yuyh.library.Base.BasePresenter;
import com.yuyh.library.Base.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface GankSearchContract
{
    interface Model extends AppBaseModel
    {
        Observable<List<SearchEntity.ResultsBean>> getSearchEntity(String queryWhat, String type, int count, int page);
    }

    interface View extends BaseView
    {
        void getSearchSuccess(List<SearchEntity.ResultsBean> resultsBeanList);

        void getSearchFailed(Throwable throwable);
    }

    abstract class Presenter extends BasePresenter<Model, View>
    {
        public abstract void getSearchData(String queryWhat, String type, int count, int page);
    }
}
