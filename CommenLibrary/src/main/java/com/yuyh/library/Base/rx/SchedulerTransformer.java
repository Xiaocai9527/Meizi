package com.yuyh.library.Base.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 肖坤 on 2017/12/9.
 *
 * @author 肖坤
 * @date 2017/12/9
 */

public class SchedulerTransformer<T> implements ObservableTransformer<T, T>
{

    @Override
    public ObservableSource<T> apply(Observable<T> upstream)
    {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
