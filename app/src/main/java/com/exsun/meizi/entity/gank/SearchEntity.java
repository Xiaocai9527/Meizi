package com.exsun.meizi.entity.gank;

import java.util.List;

/**
 * Created by xiaokun on 2017/8/9.
 */

public class SearchEntity
{
    private int count; // FIXME check this code
    private boolean error;
    private List<ResultsBean> results;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

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
         * desc : 还在用ListView？
         * ganhuo_id : 57334c9d67765903fb61c418
         * publishedAt : 2016-05-12T12:04:43.857000
         * readability : <div><div class="show-content"><blockquote>
         * <p>&#x8FD8;&#x5728;&#x7528;Lisview&#xFF1F;RecyclerView&#x90FD;&#x5DF2;&#x7ECF;&#x51FA;&#x6765;&#x4E00;&#x5E74;&#x591A;&#x4E86;&#xFF01;</p>
         * <blockquote>
         * <p>&#x60F3;&#x5FC5;&#x5927;&#x5BB6;&#x591A;&#x6216;&#x591A;&#x6216;&#x5C11;&#x7684;&#x63A5;&#x89E6;&#x8FC7;&#x6216;&#x8005;&#x4E86;&#x89E3;&#x8FC7;RecyclerView&#xFF0C;&#x4E3A;&#x4EC0;&#x4E48;&#x6CA1;&#x6709;&#x7528;&#x8D77;&#x6765;&#xFF0C;&#x539F;&#x56E0;&#x5927;&#x6982;&#x5982;&#x4E0B;&#xFF1F;</p>
         * <ul>
         * <li><strong>ListView&#x6211;&#x7528;&#x7684;&#x633A;&#x597D;&#x7684;&#xFF0C;&#x4E3A;&#x4EC0;&#x4E48;&#x8981;&#x6362;RecyclerView&#xFF1F;</strong></li>
         * <li><strong>ListView&#x7A33;&#x5B9A;&#xFF0C;&#x719F;&#x6089;&#xFF0C;&#x8FD8;&#x77E5;&#x9053;&#x5F88;&#x591A;&#x5F00;&#x6E90;&#x5E93;&#xFF0C;&#x7279;&#x522B;&#x7684;&#x597D;&#x7528;&#xFF01;</strong></li>
         * <li><strong>RecyclerView&#x4E0D;&#x80FD;&#x6DFB;&#x52A0;&#x5934;&#x90E8;&#xFF0C;ListView&#x80FD;&#xFF01;</strong></li>
         * </ul>
         * </blockquote>
         * </blockquote>
         * <h2>RecyclerView</h2>
         * <p>&#x5728;Andorid 5.0&#x51FA;&#x6765;&#x4E0D;&#x4E45;&#xFF0C;&#x6211;&#x5C31;&#x5DF2;&#x7ECF;&#x5199;&#x8FC7;RecyclerView&#x7684;&#x7B80;&#x5355;&#x4ECB;&#x7ECD;&#x4EE5;&#x53CA;&#x57FA;&#x672C;&#x4F7F;&#x7528;&#xFF0C;&#x4E0D;&#x4E86;&#x89E3;&#x7684;&#x53EF;&#x4EE5;&#x770B;&#x770B;<a href="http://blog.csdn.net/cym492224103/article/details/41719497" target="_blank">ListView&#x5347;&#x7EA7;&#x7248;RecyclerView</a>&#xFF0C;&#x4E86;&#x89E3;&#x8FC7;&#x7684;&#x540C;&#x5B66;&#x53EF;&#x4EE5;&#x5FFD;&#x7565;&#xFF0C;&#x5E76;&#x5F80;&#x4E0B;&#x770B;&#x3002;</p>
         * <ul>
         * <li>RecyclerView&#x6700;&#x5927;&#x7684;&#x4F18;&#x52BF;&#x5C31;&#x662F;&#x7075;&#x6D3B;&#xFF0C;RecyclerView&#x53EA;&#x9700;&#x6539;&#x53D8;&#x4E00;&#x884C;&#x4EE3;&#x7801;&#x5C31;&#x53EF;&#x4EE5;&#x53D8;&#x5316;&#x591A;&#x79CD;&#x4E0D;&#x540C;&#x7684;&#x5E03;&#x5C40;&#x663E;&#x793A;&#x6392;&#x7248;&#xFF0C;&#x8FD9;&#x4E00;&#x70B9;&#x5BF9;&#x4E8E;&#x5F00;&#x53D1;&#x8005;&#x662F;&#x975E;&#x5E38;&#x65B9;&#x4FBF;&#x7684;&#xFF01;</li>
         * <li>RecyclerView.Adapter&#xFF0C;&#x6BD4;BaseAdapter&#x505A;&#x4E86;&#x66F4;&#x597D;&#x7684;&#x5C01;&#x88C5;&#xFF0C;&#x628A;BaseAdapter&#x7684;getView&#x65B9;&#x6CD5;&#x62C6;&#x5206;&#x6210;onCreateViewHolder&#x65B9;&#x6CD5;&#x548C;onBindViewHolder&#x65B9;&#x6CD5;&#xFF0C;&#x5F3A;&#x5236;&#x9700;&#x8981;&#x521B;&#x5EFA;ViewHolder&#xFF0C;&#x8FD9;&#x6837;&#x7684;&#x597D;&#x5904;&#x5C31;&#x662F;&#x907F;&#x514D;&#x4E86;&#x521D;&#x5B66;&#x8005;&#x5199;&#x6027;&#x80FD;&#x4E0D;&#x4F73;&#x7684;&#x4EE3;&#x7801;</li>
         * </ul>
         * <h2>&#x5728;&#x5B9E;&#x6218;&#x4E2D;&#x6211;&#x4EEC;&#x4F1A;&#x9047;&#x5230;&#x4EC0;&#x4E48;&#x95EE;&#x9898;&#xFF1F;</h2>
         * <blockquote><p>get&#x5230;&#x4E0B;&#x9762;&#x7684;&#x6280;&#x80FD;&#x5C31;&#x80FD;&#x591F;&#x5728;&#x4F7F;&#x7528;RcyclerView&#x7684;&#x5927;&#x8DEF;&#x4E0A;&#x7545;&#x901A;&#x65E0;&#x963B;&#x4E86;&#xFF01;</p></blockquote>
         * <ul>
         * <li><strong>&#x6DFB;&#x52A0;&#x5206;&#x5272;&#x7EBF;</strong></li>
         * <li><strong>&#x6DFB;&#x52A0;&#x70B9;&#x6309;&#x6548;&#x679C;</strong></li>
         * <li><strong>&#x5217;&#x8868;&#x52A8;&#x753B;</strong></li>
         * <li><strong>&#x6539;&#x53D8;&#x67D0;&#x4E2A;&#x6570;&#x636E;&#x4FDD;&#x6301;&#x5F53;&#x524D;&#x4F4D;&#x7F6E;</strong></li>
         * <li><strong>&#x6DFB;&#x52A0;&#x5934;&#x90E8;&#x5C3E;&#x90E8;</strong></li>
         * <li><strong>&#x5217;&#x8868;&#x5206;&#x7EC4;</strong></li>
         * <li><strong>&#x5404;&#x79CD;&#x6548;&#x679C;&#x96C6;&#x6210;Demo</strong></li>
         * <li><strong>&#x66F4;&#x7075;&#x6D3B;&#x7684;RecyclerView</strong></li>
         * </ul>
         * <h3>&#x6DFB;&#x52A0;&#x5206;&#x5272;&#x7EBF;</h3>
         * <pre><code>//&#x901A;&#x8FC7;&#x4EE5;&#x4E0B;&#x65B9;&#x6CD5;&#x6DFB;&#x52A0;&#x5206;&#x5272;&#x7EBF;
         * mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));</code></pre>
         * <p>DividerItemDecoration&#x9700;&#x8981;&#x7EE7;&#x627F;RecyclerView.ItemDecoration&#x8FD9;&#x4E2A;&#x62BD;&#x8C61;&#x7C7B;&#x5B9E;&#x73B0;&#x4E00;&#x4E9B;&#x65B9;&#x6CD5; &#x3002;&#x4F46;&#x662F;&#x6211;&#x611F;&#x89C9;&#x8FC7;&#x4E8E;&#x9EBB;&#x70E6;&#xFF0C;&#x6240;&#x4EE5;&#x6211;&#x60F3;&#x5230;&#x7684;&#x662F;&#xFF1A;<br><strong>&#x76F4;&#x63A5;&#x5728;item_view&#x91CC;&#x9762;&#x5E95;&#x90E8;&#x81EA;&#x5DF1;&#x6DFB;&#x52A0;&#x4E00;&#x6839;&#x7EBF;&#x5E03;&#x5C40;&#xFF0C;&#x8FD9;&#x6837;&#x5C31;&#x65E0;&#x9700;&#x91CD;&#x5199;&#x4E86;&#xFF0C;&#x5E76;&#x4E14;&#x8FD9;&#x6837;&#x8FD8;&#x6709;&#x4E2A;&#x597D;&#x5904;&#x5C31;&#x662F;&#xFF0C;&#x5982;&#x679C;&#x7EC6;&#x5FC3;&#x7684;&#x540C;&#x5B66;&#x4F1A;&#x53D1;&#x73B0;&#xFF0C;&#x6DFB;&#x52A0;&#x5206;&#x5272;&#x7EBF;&#xFF0C;&#x6700;&#x540E;&#x4E00;&#x4E2A;item&#x4E0B;&#x9762;&#x4E0D;&#x4F1A;&#x6709;&#x5206;&#x5272;&#x7EBF;&#xFF0C;&#x663E;&#x7136;&#x5F53;&#x6570;&#x636E;&#x91CF;&#x4E0D;&#x8DB3;&#x4E00;&#x4E2A;&#x5C4F;&#x5E55;&#x7684;&#x65F6;&#x5019;&#x663E;&#x5F97;&#x5F88;&#x7A81;&#x5140;&#xFF0C;&#x4F46;&#x662F;&#x5728;item_view&#x4E0B;&#x9762;&#x6DFB;&#x52A0;&#x4E00;&#x4E2A;&#x7EBF;&#x7684;&#x5E03;&#x5C40;&#x5219;&#x4E0D;&#x4F1A;&#x51FA;&#x73B0;&#x8FD9;&#x79CD;&#x60C5;&#x51B5;</strong></p>
         * <h3>&#x6DFB;&#x52A0;&#x70B9;&#x6309;&#x6548;&#x679C;</h3>
         * <p>RecyclerView&#x76F4;&#x63A5;&#x5728;item_view&#x91CC;&#x9762;&#x914D;&#x7F6E;&#x5373;&#x53EF;selector&#x5373;&#x53EF;&#x3002;</p>
         * <h3>&#x52A8;&#x753B;</h3>
         * <blockquote><p>&#x4E00;&#x4E2A;&#x597D;&#x7684;&#x7528;&#x6237;&#x4F53;&#x9A8C;&#x5C31;&#x662F;&#x8981;&#x6709;&#x64CD;&#x4F5C;&#x52A8;&#x753B;&#x7684;&#x8FC7;&#x6E21;&#xFF0C;&#x800C;&#x4E0D;&#x662F;&#x751F;&#x786C;&#x7684;&#x5237;&#x65B0;&#x5217;&#x8868;&#x3002;</p></blockquote>
         * <p>&#x63A8;&#x8350;&#x4E00;&#x4E2A;RecyclerView&#x7684;&#x52A8;&#x753B;&#x5E93;&#xFF08;<a href="https://github.com/wasabeef/recyclerview-animators" target="_blank">recyclerview-animators</a>&#xFF09; </p><div class="image-package">
         * <img src="https://github.com/wasabeef/recyclerview-animators/raw/master/art/demo2.gif"><br><p class="image-caption">&#x8FD9;&#x91CC;&#x5199;&#x56FE;&#x7247;&#x63CF;&#x8FF0;</p>
         * </div>
         * <p>RecyclerView&#x81EA;&#x5E26;&#x6DFB;&#x52A0;&#x3001;&#x5220;&#x9664;&#x52A8;&#x753B;&#xFF0C;&#x800C;ListView&#x5219;&#x9700;&#x6DFB;&#x52A0;&#x989D;&#x5916;&#x7684;&#x4EE3;&#x7801;&#x624D;&#x80FD;&#x5B9E;&#x73B0;&#x3002;<br><strong>&#x5220;&#x9664;&#x8C03;&#x7528;RecyclerView&#x7684;adapter&#x7684;notifyItemRemoved</strong><br><strong>&#x6DFB;&#x52A0;&#x8C03;&#x7528;RecyclerView&#x7684;adapter&#x7684;notifyItemInserted</strong></p>
         * <p>&#x8BF4;&#x5230;adapter&#x6211;&#x4EEC;&#x5C31;&#x6765;&#x8BF4;&#x8BF4;RecyclerView.Adapter&#x548C;BaseAdapter&#x76F8;&#x6BD4;&#xFF0C;&#x989D;&#x5916;&#x63D0;&#x4F9B;&#x4E86;&#x4E00;&#x4E0B;&#x8FD9;&#x4E9B;&#x65B9;&#x6CD5;&#xFF1A;</p>
         * <pre><code>// &#x6570;&#x636E;&#x53D1;&#x751F;&#x4E86;&#x6539;&#x53D8;&#xFF0C;&#x90A3;&#x8C03;&#x7528;&#x8FD9;&#x4E2A;&#x65B9;&#x6CD5;&#xFF0C;&#x4F20;&#x5165;&#x6539;&#x53D8;&#x5BF9;&#x8C61;&#x7684;&#x4F4D;&#x7F6E;&#x3002;
         * public final void notifyItemChanged(int position);
         * // &#x53EF;&#x4EE5;&#x5237;&#x65B0;&#x4ECE;positionStart&#x5F00;&#x59CB;itemCount&#x6570;&#x91CF;&#x7684;item&#x4E86;
         * public final void notifyItemRangeChanged(int positionStart, int itemCount);
         * // &#x6DFB;&#x52A0;&#xFF0C;&#x4F20;&#x5165;&#x5BF9;&#x8C61;&#x7684;&#x4F4D;&#x7F6E;&#x3002;
         * public final void notifyItemInserted(int position);
         * // &#x5220;&#x9664;&#xFF0C;&#x4F20;&#x5165;&#x5BF9;&#x8C61;&#x7684;&#x4F4D;&#x7F6E;&#x3002;
         * public final void notifyItemRemoved(int position);
         * // &#x5BF9;&#x8C61;&#x4ECE;fromPosition&#x79FB;&#x52A8;&#x5230;toPosition
         * public final void notifyItemMoved(int fromPosition, int toPosition);
         * //&#x6279;&#x91CF;&#x6DFB;&#x52A0;
         * public final void notifyItemRangeInserted(int positionStart, int itemCount);
         * //&#x6279;&#x91CF;&#x5220;&#x9664;
         * public final void notifyItemRangeRemoved(int positionStart, int itemCount);</code></pre>
         * <h3>&#x6539;&#x53D8;&#x5217;&#x8868;&#x67D0;&#x4E2A;&#x5E03;&#x5C40;&#x72B6;&#x6001;&#x4E14;&#x4FDD;&#x6301;&#x5F53;&#x524D;&#x4F4D;&#x7F6E;</h3>
         * <p>&#x8FD9;&#x79CD;&#x9700;&#x6C42;&#x662F;&#x666E;&#x904D;&#x5B58;&#x5728;&#x7684;&#xFF0C;&#x5C31;&#x662F;&#x6539;&#x53D8;&#x5217;&#x8868;&#x67D0;&#x4E00;&#x4E2A;item&#x6570;&#x636E;&#xFF0C;&#x7136;&#x540E;&#x5237;&#x65B0;&#x5217;&#x8868;&#xFF0C;&#x5982;&#x679C;&#x662F;ListView&#x5237;&#x65B0;&#x540E;&#x5219;&#x4F1A;&#x56DE;&#x5230;&#x6700;&#x9876;&#x90E8;&#xFF0C;&#x800C;RecyclerView&#x540C;&#x6837;&#x7684;&#x64CD;&#x4F5C;&#x4F46;&#x662F;&#x539F;&#x6765;&#x6ED1;&#x52A8;&#x7684;&#x4F4D;&#x7F6E;&#x4E0D;&#x53D8;&#x3002;</p>
         * <p>
         * <p><a href="https://github.com/CymChad/BaseRecyclerViewAdapterHelper" target="_blank">BaseRecyclerViewAdapterHelper</a> </p>
         * <div class="image-package">
         * <img src="http://upload-images.jianshu.io/upload_images/972352-c7858feebce4b8fe.gif?imageMogr2/auto-orient/strip"><br><p class="image-caption">&#x6548;&#x679C;&#x5C55;&#x793A;</p>
         * </div>
         * <h3>&#x5E03;&#x5C40;&#x66F4;&#x7075;&#x6D3B;&#x7684;RecyclerView</h3>
         * <p><a href="https://github.com/lucasr/twoway-view" target="_blank">twoway-view</a> &#x5C01;&#x88C5;&#x4E86;RecyclerView&#x5E38;&#x7528;&#x65B9;&#x6CD5;&#xFF0C;&#x5982;click&#x7B49;&#x7B49;,&#x4EE5;&#x53CA;&#x652F;&#x6301;&#x4E86;&#x66F4;&#x591A;&#x4E0D;&#x540C;&#x7684;&#x5E03;&#x5C40;&#xFF0C;&#x4F7F;&#x5F97;RecyclerView&#x4F7F;&#x7528;&#x8D77;&#x6765;&#x66F4;&#x7B80;&#x5355;&#xFF01; </p><div class="image-package">
         * <img src="https://github.com/lucasr/twoway-view/raw/master/images/sample.png"><br><p class="image-caption">&#x8FD9;&#x91CC;&#x5199;&#x56FE;&#x7247;&#x63CF;&#x8FF0;</p>
         * </div>
         * <blockquote><p>&#x9020;&#x8D77;&#x6765;&#xFF01;&#x5C0F;&#x4F19;&#x4F34;&#x4EEC;&#xFF01;</p></blockquote>
         * <p>
         * </div>
         * </div>
         * type : Android
         * url : http://www.jianshu.com/p/a92955be0a3e
         * who : 陈宇明
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }

        public String getGanhuo_id()
        {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id)
        {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt()
        {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt)
        {
            this.publishedAt = publishedAt;
        }

        public String getReadability()
        {
            return readability;
        }

        public void setReadability(String readability)
        {
            this.readability = readability;
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
