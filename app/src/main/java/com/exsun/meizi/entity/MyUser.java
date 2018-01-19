package com.exsun.meizi.entity;

import cn.bmob.v3.BmobUser;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/19
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class MyUser extends BmobUser
{
    //weizhi
    private String location;
    //nicheng
    private String nickName;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
