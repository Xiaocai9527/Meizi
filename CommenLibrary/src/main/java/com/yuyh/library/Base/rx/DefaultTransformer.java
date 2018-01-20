package com.yuyh.library.Base.rx;

import android.content.Context;

import com.yuyh.library.Base.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import retrofit2.Response;

/**
 * Created by 肖坤 on 2017/12/9.
 *
 * @author 肖坤
 * @date 2017/12/9
 */

public class DefaultTransformer<R extends BaseResponse> implements
        ObservableTransformer<Response<R>, Response<R>>
{
    private Context context;

    public DefaultTransformer(Context context)
    {
        this.context = context;
    }

    @Override
    public ObservableSource<Response<R>> apply(Observable<Response<R>> upstream)
    {
        return upstream.compose(new SchedulerTransformer<Response<R>>());

    }
}
