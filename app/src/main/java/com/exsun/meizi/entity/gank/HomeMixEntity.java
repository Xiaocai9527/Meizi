package com.exsun.meizi.entity.gank;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by xiaokun on 2017/7/27.
 */
@Table("meizhis")
public class HomeMixEntity implements Serializable
{

//    private static final long serialVersionUID = 2711720190926523247L;

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    private int id;

    @Column("url")
    public String url;
    @Column("date")
    public String date;
    @Column("desc")
    public String desc;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return "HomeMixEntity{" +
                "url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
