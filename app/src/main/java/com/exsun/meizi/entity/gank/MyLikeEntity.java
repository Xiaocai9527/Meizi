package com.exsun.meizi.entity.gank;

import com.exsun.meizi.entity.MyUser;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xiaokun on 2017/8/8.
 */

public class MyLikeEntity extends BmobObject implements Serializable
{
    private String url;
    private String desc;
    private String author;
    private MyUser myUser;
    /**
     * 收藏标志位
     */
    private boolean isCancel;

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public MyUser getMyUser()
    {
        return myUser;
    }

    public void setMyUser(MyUser myUser)
    {
        this.myUser = myUser;
    }

    public boolean isCancel()
    {
        return isCancel;
    }

    public void setCancel(boolean cancel)
    {
        isCancel = cancel;
    }
}
