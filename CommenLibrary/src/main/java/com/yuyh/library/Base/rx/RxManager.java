package com.yuyh.library.Base.rx;

/**
 * Created by Administrator on 2016/12/31.
 */

public class RxManager
{
//    public RxBus mRxBus = RxBus.$();
//    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察源
//    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();// 管理订阅者
//
//
//    public void on(String eventName, Action1<Object> action1)
//    {
//        Observable<?> mObservable = mRxBus.register(eventName);
//        mObservables.put(eventName, mObservable);
//        mCompositeSubscription
//                .add(mObservable.observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(action1, new Action1<Throwable>()
//                        {
//                            @Override
//                            public void call(Throwable throwable)
//                            {
//                                throwable.printStackTrace();
//                            }
//                        }));
//    }
//
//    public void add(Subscription m)
//    {
//        mCompositeSubscription.add(m);
//    }
//
//    public void clear()
//    {
//        mCompositeSubscription.unsubscribe();// 取消订阅
//        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
//            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
//    }
//
//    public void post(Object tag, Object content)
//    {
//        mRxBus.post(tag, content);
//    }
}
