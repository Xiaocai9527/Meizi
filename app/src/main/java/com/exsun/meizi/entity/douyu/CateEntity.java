package com.exsun.meizi.entity.douyu;

public class CateEntity
{
    private String cate_id;
    private String cate_name;
    private String push_ios;
    private String push_nearby;
    private String push_show;
    private String push_vertical_screen;
    private String short_name;

    public String getCate_id()
    {
        return this.cate_id;
    }

    public void setCate_id(String cate_id)
    {
        this.cate_id = cate_id;
    }

    public String getCate_name()
    {
        return this.cate_name;
    }

    public void setCate_name(String cate_name)
    {
        this.cate_name = cate_name;
    }

    public String getShort_name()
    {
        return this.short_name;
    }

    public void setShort_name(String short_name)
    {
        this.short_name = short_name;
    }

    public String getPush_ios()
    {
        return this.push_ios;
    }

    public void setPush_ios(String push_ios)
    {
        this.push_ios = push_ios;
    }

    public String getPush_show()
    {
        return this.push_show;
    }

    public void setPush_show(String push_show)
    {
        this.push_show = push_show;
    }

    public String getPush_vertical_screen()
    {
        return this.push_vertical_screen;
    }

    public void setPush_vertical_screen(String push_vertical_screen)
    {
        this.push_vertical_screen = push_vertical_screen;
    }

    public String getPush_nearby()
    {
        return this.push_nearby;
    }

    public void setPush_nearby(String push_nearby)
    {
        this.push_nearby = push_nearby;
    }
}
