package com.exsun.meizi.entity;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/4.
 */

public class AndroidMixEntity
{

    private String url;
    private List<GankCategoryEntity.ResultsBean> results;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<GankCategoryEntity.ResultsBean> getResults()
    {
        return results;
    }

    public void setResults(List<GankCategoryEntity.ResultsBean> results)
    {
        this.results = results;
    }

    public static class ResultsBean
    {
        /**
         * _id : 5976f07c421aa90ca3bb6b66
         * createdAt : 2017-07-25T15:17:16.832Z
         * desc : Android 面包屑导航。
         * images : ["http://img.gank.io/d3283249-cb2d-4251-9ed2-ac5e08d18ed4"]
         * publishedAt : 2017-07-25T15:25:42.391Z
         * source : chrome
         * type : Android
         * url : https://github.com/fython/BreadcrumbsView
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id()
        {
            return _id;
        }

        public void set_id(String _id)
        {
            this._id = _id;
        }

        public String getCreatedAt()
        {
            return createdAt;
        }

        public void setCreatedAt(String createdAt)
        {
            this.createdAt = createdAt;
        }

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }

        public String getPublishedAt()
        {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt)
        {
            this.publishedAt = publishedAt;
        }

        public String getSource()
        {
            return source;
        }

        public void setSource(String source)
        {
            this.source = source;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public boolean isUsed()
        {
            return used;
        }

        public void setUsed(boolean used)
        {
            this.used = used;
        }

        public String getWho()
        {
            return who;
        }

        public void setWho(String who)
        {
            this.who = who;
        }

        public List<String> getImages()
        {
            return images;
        }

        public void setImages(List<String> images)
        {
            this.images = images;
        }
    }
}
