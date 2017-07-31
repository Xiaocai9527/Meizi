package com.yuyh.library.Base.rx;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by Administrator on 2016/12/31.
 */

public class RxBus
{
    private static RxBus instance;

    public static synchronized RxBus $()
    {
        if (null == instance)
        {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus()
    {
    }

    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<Object, List<Subject>>();

    /**
     * 订阅事件源
     *
     * @param mObservable
     * @param mAction1
     * @return
     */
    public RxBus OnEvent(Observable<?> mObservable, Consumer<Object> mAction1)
    {
        mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAction1, new Consumer<Throwable>()
                {
                    @Override
                    public void accept(Throwable throwable) throws Exception
                    {
                        throwable.printStackTrace();
                    }
                });
        return $();
    }

    /**
     * 注册事件源
     *
     * @param tag
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public <T> Observable<T> register(@NonNull Object tag)
    {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList)
        {
            subjectList = new ArrayList<Subject>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        //LogUtil.d("register", tag + "  size:" + subjectList.size());
        return subject;
    }

    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag)
    {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects)
        {
            subjectMapper.remove(tag);
        }
    }

    /**
     * 取消监听
     *
     * @param tag
     * @param observable
     * @return
     */
    @SuppressWarnings("rawtypes")
    public RxBus unregister(@NonNull Object tag,
                            @NonNull Observable<?> observable)
    {
        if (null == observable)
            return $();
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects)
        {
            subjects.remove((Subject<?>) observable);
            if (isEmpty(subjects))
            {
                subjectMapper.remove(tag);
                //LogUtil.d("unregister", tag + "  size:" + subjects.size());
            }
        }
        return $();
    }

    public void post(@NonNull Object content)
    {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     *
     * @param content
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void post(@NonNull Object tag, @NonNull Object content)
    {
        //LogUtil.d("post", "eventName: " + tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList))
        {
            for (Subject subject : subjectList)
            {
                subject.onNext(content);
                //LogUtil.d("onEvent", "eventName: " + tag);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection)
    {
        return null == collection || collection.isEmpty();
    }
}
