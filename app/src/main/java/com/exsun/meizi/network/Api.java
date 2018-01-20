package com.exsun.meizi.network;

import android.util.SparseArray;

import com.exsun.meizi.BuildConfig;
import com.exsun.meizi.config.Constant;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class Api
{
    public static final int GANK = 0;
    public static final int DOUYU = 1;
    public Retrofit retrofit;
    public ApiService apiService;
    public DouyuApiService douyuApiService;

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 20000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>();
    private final OkHttpClient okHttpClient;

    private Api(int hostType)
    {
        HttpLoggingInterceptor logInterceptor = null;
        if (BuildConfig.DEBUG)
        {
            //开启Log
            logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        this.okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
//                .addInterceptor(new GzipRequsetInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        String baseUrl = "";
        if (hostType == GANK)
        {
            baseUrl = Constant.ROOT_PATH;
        } else if (hostType == DOUYU)
        {
            baseUrl = Constant.DOUYU_BASE_URL;
        }

        retrofit = new Retrofit.Builder().client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl).build();

        apiService = retrofit.create(ApiService.class);
        douyuApiService = retrofit.create(DouyuApiService.class);
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

    public static DouyuApiService getDouyuService(int host)
    {
        Api api = sRetrofitManager.get(host);
        if (api == null)
        {
            api = new Api(host);
            sRetrofitManager.put(host, api);
        }
        return api.douyuApiService;
    }

    /**
     * 添加gzip压缩拦截器
     */
    private static class GzipRequsetInterceptor implements Interceptor
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null)
            {
                return chain.proceed(originalRequest);
            }
            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();
            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body)
        {
            return new RequestBody()
            {
                @Override
                public MediaType contentType()
                {
                    return body.contentType();
                }

                @Override
                public long contentLength() throws IOException
                {
                    return -1;
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException
                {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }

}
