package com.exsun.meizi.entity.gank;

import java.io.Serializable;

/**
 * Created by xiaokun on 2017/8/8.
 */

public class MyLikeEntity implements Serializable
{
    private String url;
    private String desc;
    private String author;

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
}
