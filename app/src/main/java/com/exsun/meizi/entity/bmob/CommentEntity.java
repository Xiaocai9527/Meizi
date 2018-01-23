package com.exsun.meizi.entity.bmob;

import cn.bmob.v3.BmobObject;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/23
 *     描述   : 评论表
 *     版本   : 1.0
 * </pre>
 */

public class CommentEntity extends BmobObject
{
    //评论的人
    private MyUser user;
    //评论人的昵称
    private String nickName;
    //评论的动态
    private TalkMoodEntity talkMoodEntity;
    //回复给谁
    private MyUser replyTo;
    //被回复人的昵称
    private String toNikcName;
    //回复内容
    private String content;

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getToNikcName()
    {
        return toNikcName;
    }

    public void setToNikcName(String toNikcName)
    {
        this.toNikcName = toNikcName;
    }

    public MyUser getUser()
    {
        return user;
    }

    public void setUser(MyUser reply)
    {
        this.user = reply;
    }

    public TalkMoodEntity getTalkMoodEntity()
    {
        return talkMoodEntity;
    }

    public void setTalkMoodEntity(TalkMoodEntity talkMoodEntity)
    {
        this.talkMoodEntity = talkMoodEntity;
    }

    public MyUser getReplyTo()
    {
        return replyTo;
    }

    public void setReplyTo(MyUser replyTo)
    {
        this.replyTo = replyTo;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
