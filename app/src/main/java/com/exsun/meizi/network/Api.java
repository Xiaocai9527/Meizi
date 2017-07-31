package com.exsun.meizi.network;

import android.util.SparseArray;

import com.exsun.meizi.config.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class Api
{
    public static final int RELEASE = 0;
    public Retrofit retrofit;
    public ApiService apiService;

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 20000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(RELEASE);
    private final OkHttpClient okHttpClient;

    private Api(int hostType)
    {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
                .build();

        retrofit = new Retrofit.Builder().client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.ROOT_PATH).build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getDefault(int host)
    {
        Api api = sRetrofitManager.get(host);
        if (api == null)
        {
            api = new Api(host);
            sRetrofitManager.put(host, api);
        }
        return api.apiService;
    }
}
