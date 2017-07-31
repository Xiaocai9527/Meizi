package com.exsun.meizi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaokun on 2017/7/26.
 */

public class GankDayEntity implements Serializable
{

    /**
     * category : ["休息视频","前端","福利","App","拓展资源","Android","iOS"]
     * error : false
     * results : {"Android":[{"_id":"5976f07c421aa90ca3bb6b66","createdAt":"2017-07-25T15:17:16.832Z","desc":"Android 面包屑导航。","images":["http://img.gank.io/d3283249-cb2d-4251-9ed2-ac5e08d18ed4"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"Android","url":"https://github.com/fython/BreadcrumbsView","used":true,"who":"代码家"},{"_id":"5976f104421aa90c9203d400","createdAt":"2017-07-25T15:19:32.977Z","desc":"HenCoder 绘制 3 练习项目","images":["http://img.gank.io/5f19a7f6-580b-4112-bb11-57a09a45ef95","http://img.gank.io/28f715ed-bfcb-4c8f-b024-ee421eb03e11"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"Android","url":"http://hencoder.com/ui-1-3/","used":true,"who":"代码家"}],"App":[{"_id":"5976a465421aa97de5c7c9a7","createdAt":"2017-07-25T09:52:37.993Z","desc":"MusicDNA Android版","images":["http://img.gank.io/2dbdb3b3-b547-4518-8263-22fd2de316cd"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"App","url":"https://github.com/harjot-oberai/MusicDNA","used":true,"who":"galois"}],"iOS":[{"_id":"5976f13b421aa90ca209c4de","createdAt":"2017-07-25T15:20:27.841Z","desc":"Delightful framework for iOS to easily persist structs, images, and data.","images":["http://img.gank.io/e69a552e-c5d0-4096-b02a-c787b4a95c72"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"iOS","url":"https://github.com/saoudrizwan/Disk","used":true,"who":"S"}],"休息视频":[{"_id":"5973521e421aa90ca3bb6b56","createdAt":"2017-07-22T21:24:46.388Z","desc":"【牛叔】几分钟看科幻片《终结者4》谁才是这个世界的救世主","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av12465641/","used":true,"who":"LHF"}],"前端":[{"_id":"59759750421aa90c9203d3f5","createdAt":"2017-07-24T14:44:32.993Z","desc":"基于 Vue 2.x 和 GitHub Issue 实现的博客系统","images":["http://img.gank.io/94b512c9-becd-4dc4-899d-e7356aef13b4"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"web","type":"前端","url":"https://github.com/bingoogolapple/BGAIssueBlog","used":true,"who":"王浩"},{"_id":"5975ad23421aa90ca209c4cd","createdAt":"2017-07-24T16:17:39.631Z","desc":"一个优雅的 Hyper Terminal 主题","images":["http://img.gank.io/a2c0e90c-e01c-4b1f-81c3-fc055b15de0a"],"publishedAt":"2017-07-25T15:25:42.391Z","source":"web","type":"前端","url":"https://github.com/wl9739/hyper-qing","used":true,"who":null},{"_id":"5975b228421aa90ca3bb6b63","createdAt":"2017-07-24T16:39:04.501Z","desc":"前端每周清单第 23 期：React 内部原理与实现，自定义基于 JavaScript 的 16 位虚拟机","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/28076241","used":true,"who":"王下邀月熊"}],"拓展资源":[{"_id":"5976a615421aa90ca209c4d8","createdAt":"2017-07-25T09:59:49.672Z","desc":"腾讯跨平台高性能神经网络前向计算框架","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"拓展资源","url":"https://github.com/Tencent/ncnn","used":true,"who":"galois"},{"_id":"5976f0ba421aa90ca3bb6b67","createdAt":"2017-07-25T15:18:18.219Z","desc":"弱密码 Wifi 破解思路导引。","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"拓展资源","url":"https://github.com/brannondorsey/wifi-cracking","used":true,"who":"S"}],"福利":[{"_id":"59761946421aa90ca209c4d5","createdAt":"2017-07-24T23:59:02.992Z","desc":"7-25","publishedAt":"2017-07-25T15:25:42.391Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fhvf13o2eoj20u011hjx6.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError()
    {
        return error;
    }

    public void setError(boolean error)
    {
        this.error = error;
    }

    public ResultsBean getResults()
    {
        return results;
    }

    public void setResults(ResultsBean results)
    {
        this.results = results;
    }

    public List<String> getCategory()
    {
        return category;
    }

    public void setCategory(List<String> category)
    {
        this.category = category;
    }

    public static class ResultsBean implements Serializable
    {
        private List<AndroidBean> Android;
        private List<AppBean> App;
        private List<IOSBean> iOS;
        private List<VideoBean> video;
        private List<FrontBean> front;
        private List<ResourcesBean> resources;
        private List<WelfareBean> welfare;

        public List<AndroidBean> getAndroid()
        {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android)
        {
            this.Android = Android;
        }

        public List<AppBean> getApp()
        {
            return App;
        }

        public void setApp(List<AppBean> App)
        {
            this.App = App;
        }

        public List<IOSBean> getIOS()
        {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS)
        {
            this.iOS = iOS;
        }

        public List<VideoBean> getVideo()
        {
            return video;
        }

        public void setVideo(List<VideoBean> video)
        {
            this.video = video;
        }

        public List<FrontBean> getFront()
        {
            return front;
        }

        public void setFront(List<FrontBean> front)
        {
            this.front = front;
        }

        public List<ResourcesBean> getResources()
        {
            return resources;
        }

        public void setResources(List<ResourcesBean> resources)
        {
            this.resources = resources;
        }

        public List<WelfareBean> getWelfare()
        {
            return welfare;
        }

        public void setWelfare(List<WelfareBean> welfare)
        {
            this.welfare = welfare;
        }

        public static class AndroidBean implements Serializable
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

        public static class AppBean implements Serializable
        {
            /**
             * _id : 5976a465421aa97de5c7c9a7
             * createdAt : 2017-07-25T09:52:37.993Z
             * desc : MusicDNA Android版
             * images : ["http://img.gank.io/2dbdb3b3-b547-4518-8263-22fd2de316cd"]
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : chrome
             * type : App
             * url : https://github.com/harjot-oberai/MusicDNA
             * used : true
             * who : galois
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

        public static class IOSBean implements Serializable
        {
            /**
             * _id : 5976f13b421aa90ca209c4de
             * createdAt : 2017-07-25T15:20:27.841Z
             * desc : Delightful framework for iOS to easily persist structs, images, and data.
             * images : ["http://img.gank.io/e69a552e-c5d0-4096-b02a-c787b4a95c72"]
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : chrome
             * type : iOS
             * url : https://github.com/saoudrizwan/Disk
             * used : true
             * who : S
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

        public static class VideoBean implements Serializable
        {
            /**
             * _id : 5973521e421aa90ca3bb6b56
             * createdAt : 2017-07-22T21:24:46.388Z
             * desc : 【牛叔】几分钟看科幻片《终结者4》谁才是这个世界的救世主
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : chrome
             * type : 休息视频
             * url : http://www.bilibili.com/video/av12465641/
             * used : true
             * who : LHF
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
        }

        public static class FrontBean implements Serializable
        {
            /**
             * _id : 59759750421aa90c9203d3f5
             * createdAt : 2017-07-24T14:44:32.993Z
             * desc : 基于 Vue 2.x 和 GitHub Issue 实现的博客系统
             * images : ["http://img.gank.io/94b512c9-becd-4dc4-899d-e7356aef13b4"]
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : web
             * type : 前端
             * url : https://github.com/bingoogolapple/BGAIssueBlog
             * used : true
             * who : 王浩
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

        public static class ResourcesBean implements Serializable
        {
            /**
             * _id : 5976a615421aa90ca209c4d8
             * createdAt : 2017-07-25T09:59:49.672Z
             * desc : 腾讯跨平台高性能神经网络前向计算框架
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : chrome
             * type : 拓展资源
             * url : https://github.com/Tencent/ncnn
             * used : true
             * who : galois
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
        }

        public static class WelfareBean implements Serializable
        {
            /**
             * _id : 59761946421aa90ca209c4d5
             * createdAt : 2017-07-24T23:59:02.992Z
             * desc : 7-25
             * publishedAt : 2017-07-25T15:25:42.391Z
             * source : chrome
             * type : 福利
             * url : https://ws1.sinaimg.cn/large/610dc034gy1fhvf13o2eoj20u011hjx6.jpg
             * used : true
             * who : daimajia
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
        }
    }
}
