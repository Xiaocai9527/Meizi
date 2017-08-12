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

public class RxBus {

    private static RxBus instance;

    /**
     * ConcurrentHashMap: 线程安全集合
     *   Subject 同时充当了Observer和Observable的角色
     */
    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();


    public static synchronized RxBus getInstance() {
        if(null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {
    }

    /**
     * 订阅事件源
     *
     * @param observable
     * @param consumer
     * @return
     */
    public RxBus onEvent(Observable<?> observable, Consumer<Object> consumer) {
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        return getInstance();
    }


    /**
     * 注册事件源
     *
     * @param tag key
     * @param <T>
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if(null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T> subject = PublishSubject.create();
        subjectList.add(subject);
        // LogUtil.log("register" + tag + " size:" + subjectList.size());
        return subject;
    }

    /**
     * 取消整个tag的监听
     *
     * @param tag key
     */
    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if(null != subjectList) {
            subjectMapper.remove(tag);
        }
    }

    /**
     * 取消tag里某个observable的监听
     *
     * @param tag key
     * @param observable 要删除的observable
     * @return
     */
    @SuppressWarnings("rawtypes")
    public RxBus unregister(@NonNull Object tag,
                            @NonNull Observable<?> observable) {
        if(null == observable) {
            return getInstance();
        }

        List<Subject> subjectList = subjectMapper.get(tag);
        if(null != subjectList) {
            // 从subjectList中删去observable
            subjectList.remove((Subject<?>) observable);
            // 若此时subjectList为空则从subjectMapper中删去
            if(isEmpty(subjectList)) {
                subjectMapper.remove(tag);
            }
        }
        return getInstance();
    }

    /**
     * 触发事件
     *
     * @param content
     */
    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     *
     * @param tag key
     * @param content
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if(!isEmpty(subjectList)) {
            for(Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
    }


    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

}
