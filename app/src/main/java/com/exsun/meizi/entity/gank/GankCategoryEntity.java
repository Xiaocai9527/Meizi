package com.exsun.meizi.entity.gank;

import java.util.List;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class GankCategoryEntity
{

    /**
     * error : false
     * results : [{"_id":"5976f07c421aa90ca3bb6b66","createdAt":"2017-07-25T15:17:16.832Z","desc":"Android 面包屑导航。","images":["http://img.gank.io/d3283249-cb2d-4251-9ed2-ac5e08d18ed4"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"Android","url":"https://github.com/fython/BreadcrumbsView","used":true,"who":"代码家"},{"_id":"5976f104421aa90c9203d400","createdAt":"2017-07-25T15:19:32.977Z","desc":"HenCoder 绘制 3 练习项目","images":["http://img.gank.io/5f19a7f6-580b-4112-bb11-57a09a45ef95","http://img.gank.io/28f715ed-bfcb-4c8f-b024-ee421eb03e11"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"Android","url":"http://hencoder.com/ui-1-3/","used":true,"who":"代码家"},{"_id":"5973f95d421aa90c9203d3eb","createdAt":"2017-07-23T09:18:21.828Z","desc":"Android 层叠卡片控件，仿\"探探app\"","images":["http://img.gank.io/36e2eb02-be78-4c97-950b-28020d1e0356"],"publishedAt":"2017-07-24T12:13:11.280Z","source":"chrome","type":"Android","url":"https://github.com/fashare2015/StackLayout","used":true,"who":"Jason"},{"_id":"59746449421aa90ca209c4c0","createdAt":"2017-07-23T16:54:33.986Z","desc":"Java 时间与日期处理","publishedAt":"2017-07-24T12:13:11.280Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/28055974","used":true,"who":"王下邀月熊"},{"_id":"5975480b421aa97de5c7c999","createdAt":"2017-07-24T09:06:19.690Z","desc":"RxJava2.X 源码解析（一）： 探索RxJava2分发订阅流程","publishedAt":"2017-07-24T12:13:11.280Z","source":"web","type":"Android","url":"http://url.cn/4CM8ka6","used":true,"who":"陈宇明"},{"_id":"59754c7f421aa97de5c7c99b","createdAt":"2017-07-24T09:25:19.617Z","desc":"强大和智能的RefreshLayout，支持越界回弹，集成了几十种炫酷的Header和 Footer","publishedAt":"2017-07-24T12:13:11.280Z","source":"web","type":"Android","url":"https://segmentfault.com/a/1190000010066071","used":true,"who":"树朾"},{"_id":"5975717d421aa90ca3bb6b60","createdAt":"2017-07-24T12:03:09.539Z","desc":"Android 简洁优雅的文件选择器。","images":["http://img.gank.io/b3771674-25a1-478a-b665-f0f0960f80a4"],"publishedAt":"2017-07-24T12:13:11.280Z","source":"chrome","type":"Android","url":"https://github.com/FirzenYogesh/FileListerDialog","used":true,"who":"代码家"},{"_id":"5964c8ff421aa90ca3bb6ae1","createdAt":"2017-07-11T20:47:59.353Z","desc":"理解与设计自适应图标 \u2014\u2014 自适应图标入门指南","publishedAt":"2017-07-21T12:39:43.370Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/27814686","used":true,"who":"galois"},{"_id":"5969a267421aa90ca209c46a","createdAt":"2017-07-15T13:04:39.224Z","desc":"Android源码完全解析\u2014\u2014View的Measure过程","publishedAt":"2017-07-21T12:39:43.370Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/4a68f9dc8f7c","used":true,"who":null},{"_id":"5971719e421aa97de5c7c97d","createdAt":"2017-07-21T11:14:38.609Z","desc":"一款非常漂亮的 Material Design 风格的音乐播放器！超棒！","images":["http://img.gank.io/9f05efe7-3196-4de4-af65-24e0a919a584"],"publishedAt":"2017-07-21T12:39:43.370Z","source":"chrome","type":"Android","url":"https://github.com/aliumujib/Orin","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public List<ResultsBean> getResults()
    {
        return results;
    }

    public void setResults(List<ResultsBean> results)
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
