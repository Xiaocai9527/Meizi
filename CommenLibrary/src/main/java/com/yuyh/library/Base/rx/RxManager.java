package com.yuyh.library.Base.rx;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2016/12/31.
 */

public class RxManager
{

    public RxBus mRxBus = RxBus.getInstance();

    /**
     * 管理观察源
     */
    private Map<String, Observable<?>> mObservableMap = new HashMap<>();

    /**
     * 管理订阅者
     */
    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();


    public void on(String eventName, Consumer<Object> consumer)
    {
        // 注册
        Observable<?> mObservable = mRxBus.register(eventName);

        mObservableMap.put(eventName, mObservable);

        mCompositeSubscription
                .add(mObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, new Consumer<Throwable>()
                        {
                            @Override
                            public void accept(Throwable throwable) throws Exception
                            {
                                throwable.printStackTrace();
                            }
                        }));

    }

    /**
     * 添加订阅者到mCompositeSubscription
     *
     * @param m 要添加的订阅者
     */
    public void add(Disposable m)
    {
        mCompositeSubscription.add(m);
    }

    /**
     * 取消所有注册
     */
    public void clear()
    {
        // 取消订阅
        mCompositeSubscription.dispose();
        for (Map.Entry<String, Observable<?>> entry : mObservableMap.entrySet())
        {
            // 取消注册
            mRxBus.unregister(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 触发事件
     *
     * @param tag
     * @param content
     */
    public void post(Object tag, Object content)
    {
        mRxBus.post(tag, content);
    }

}