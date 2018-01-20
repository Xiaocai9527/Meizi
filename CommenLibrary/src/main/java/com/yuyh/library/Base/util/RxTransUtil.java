package com.yuyh.library.Base.util;

import android.content.Context;
import android.support.annotation.Keep;

import com.yuyh.library.Base.BaseEntity;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xiaokun
 * @date 2017/12/12
 */
@Keep
public class RxTransUtil
{
    private static final int SUCCESS = 200;
    public static final int TOKEN_INVAILD = 201;
    public static final int NO_LOGIN = 202;

    /**
     * 统一切换线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper()
    {
        return new ObservableTransformer<T, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream)
            {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> ObservableTransformer<BaseEntity<T>, T> hanleResult()
    {
        return new ObservableTransformer<BaseEntity<T>, T>()
        {
            @Override
            public ObservableSource<T> apply(final Observable<BaseEntity<T>> upstream)
            {

                return upstream.flatMap(new Function<BaseEntity<T>, ObservableSource<T>>()
                {
                    @Override
                    public ObservableSource<T> apply(BaseEntity<T> tBaseEntity) throws Exception
                    {
                        /**
                         * 这里可以对错误进行判断统一处理数据
                         *
                         * 这里简单处理就没有判断先
                         */
                        int code = tBaseEntity.getCode();
                        if (code == SUCCESS)
                        {
                            T result = tBaseEntity.getResult();
                            createData(result);
                            return createData(result);

                        }
//                        else if (code == TOKEN_INVAILD)
//                        {
//
//                        } else if (code == NO_LOGIN)
//                        {
//
//                        }
                        else
                        {
                            //方案1：
                            return Observable.error(new IOException(tBaseEntity.getMsg()));
                            //方案2：
//                            throw new ServerException(tBaseEntity.getCode(), tBaseEntity.getMsg());
                        }
                    }
                });
            }
        };
    }

    /**
     * 假设有token过期需要跳转登录页的，需要传context Activity
     *
     * @param context
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseEntity<T>, T> hanleResult(final Context context)
    {
        return new ObservableTransformer<BaseEntity<T>, T>()
        {
            @Override
            public ObservableSource<T> apply(Observable<BaseEntity<T>> upstream)
            {

                return upstream.flatMap(new Function<BaseEntity<T>, ObservableSource<T>>()
                {
                    @Override
                    public ObservableSource<T> apply(BaseEntity<T> tBaseEntity) throws Exception
                    {
                        /**
                         * 这里可以对错误进行判断统一处理数据
                         *
                         * 这里简单处理就没有判断先
                         */
                        int code = tBaseEntity.getCode();
                        if (code == SUCCESS)
                        {
                            T result = tBaseEntity.getResult();
                            createData(result);
                            return createData(result);
                        }
//                        else if (code == TOKEN_INVAILD)
//                        {
//                            //可以放在okhttp拦截器中进行处理
//                        }
//                        else if (code == NO_LOGIN)
//                        {
//                            Intent intent = new Intent(context, LoginAcitivity.class);
//                            context.startActivity(intent);
//                            return Observable.empty();
//                        }
                        else
                        {
                            //方案1：
                            return Observable.error(new IOException(tBaseEntity.getMsg()));
                            //方案2：
//                            throw new ServerException(tBaseEntity.getCode(), tBaseEntity.getMsg());
                        }
                    }
                });
            }
        };
    }

    /**
     * 创建需要的数据流T
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t)
    {
        return Observable.create(new ObservableOnSubscribe<T>()
        {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception
            {
                try
                {
                    e.onNext(t);
                    e.onComplete();
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                    e.onError(e1);
                }
            }
        });
    }

}
