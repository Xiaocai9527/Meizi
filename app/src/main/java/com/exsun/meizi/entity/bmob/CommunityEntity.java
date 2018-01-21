package com.exsun.meizi.entity.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 肖坤 on 2018/1/20.
 * <p>
 * 老铁社区实体类
 *
 * @author 肖坤
 * @date 2018/1/20
 */

public class CommunityEntity extends BmobObject
{
    //昵称
    private String author;
    //内容
    private String content;
    //时间
    private String time;

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
