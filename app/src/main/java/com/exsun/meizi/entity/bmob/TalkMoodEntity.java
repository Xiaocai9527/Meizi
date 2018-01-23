package com.exsun.meizi.entity.bmob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/22
 *     描述   : 说说实体类
 *     版本   : 1.0
 * </pre>
 */

public class TalkMoodEntity extends BmobObject
{
    //对应的用户
    private MyUser user;
    //发表时间
    private String publishedTime;
    //发表内容
    private String content;
    //位置
    private String location;
    //昵称
    private String nickName;
    // 多对多 点赞的所有用户(用户也可以点赞多个帖子)
    private BmobRelation likes;

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public MyUser getUser()
    {
        return user;
    }

    public void setUser(MyUser user)
    {
        this.user = user;
    }

    public String getPublishedTime()
    {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime)
    {
        this.publishedTime = publishedTime;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public BmobRelation getLikes()
    {
        return likes;
    }

    public void setLikes(BmobRelation likes)
    {
        this.likes = likes;
    }
}
