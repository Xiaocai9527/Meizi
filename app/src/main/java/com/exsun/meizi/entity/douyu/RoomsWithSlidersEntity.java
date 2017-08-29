package com.exsun.meizi.entity.douyu;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/28.
 */

public class RoomsWithSlidersEntity
{
    private List<RoomsEntity.DataBean> roomData;

    private List<SlidersEntity.DataBean> sliderData;

    public List<RoomsEntity.DataBean> getRoomData()
    {
        return roomData;
    }

    public void setRoomData(List<RoomsEntity.DataBean> roomData)
    {
        this.roomData = roomData;
    }

    public List<SlidersEntity.DataBean> getSliderData()
    {
        return sliderData;
    }

    public void setSliderData(List<SlidersEntity.DataBean> sliderData)
    {
        this.sliderData = sliderData;
    }
}
