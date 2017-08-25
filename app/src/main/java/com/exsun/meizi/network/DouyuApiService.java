package com.exsun.meizi.network;

import com.exsun.meizi.entity.douyu.CateEntity;
import com.exsun.meizi.entity.douyu.ChannelEntity;
import com.exsun.meizi.entity.douyu.RoomEntity;
import com.exsun.meizi.entity.douyu.RoomInfoEntity;
import com.exsun.meizi.entity.douyu.RoomInfoEntity2;
import com.exsun.meizi.entity.douyu.SearchEntity;
import com.exsun.meizi.entity.douyu.SliderEntity;
import com.exsun.meizi.entity.douyu.TagEntity;
import com.exsun.meizi.entity.douyu.TempLiveEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xiaokun on 2017/8/25.
 */

public interface DouyuApiService
{
    /**
     * 获取斗鱼频道频道信息
     *
     * @return
     */
    @GET("api/v1/game")
    Observable<List<ChannelEntity>> getChannels();

    /**
     * 获取斗鱼房间信息
     *
     * @param str
     * @param i
     * @param i2
     * @return
     */
    @GET("api/v1/live/{cate_id}")
    Observable<List<RoomEntity>> getRooms(@Path("cate_id") String str, @Query("limit") int i, @Query("offset") int i2);

    /**
     * 首页轮播图
     *
     * @return
     */
    @GET("api/v1/slide")
    Observable<List<SliderEntity>> getSliders();

    @GET("api/v1/getColumnList")
    Observable<List<CateEntity>> getCateList();

    @GET("api/v1/getColumnDetail")
    Observable<List<TagEntity>> getTagList(@Query("shortName") String str);

    @POST("api/v1/room/{roomid}")
    Observable<RoomInfoEntity2> getLiveInfo(@Path("roomid") String str, @Query("aid") String str2, @Query("cdn") String str3, @Query("client_sys") String str4, @Query("time") String str5, @Query("auth") String str6);

    @GET("swf_api/room/{room_id}")
    Observable<RoomInfoEntity> getRoomInfo(@Path("room_id") String str, @Query("cdn") String str2, @Query("nofan") String str3, @Query("_t") String str4, @Query("sign") String str5);

    @GET("html5/live")
    Observable<TempLiveEntity> getTempLiveInfo(@Query("roomId") String str);

    /**
     * 斗鱼房间搜索api
     *
     * @param str
     * @param i
     * @param i2
     * @return
     */
    @GET("api/v1/searchNew/{search_string}/1")
    Observable<SearchEntity> search(@Path("search_string") String str, @Query("limit") int i, @Query("offset") int i2);
}
