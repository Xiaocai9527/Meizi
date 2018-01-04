package com.exsun.meizi.ui.home.fragment.guolin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gongzhonghao.BlogEntity;
import com.exsun.meizi.helper.Toasts;
import com.exsun.meizi.helper.utils;
import com.exsun.meizi.ui.home.adapter.BlogAdapter;
import com.yuyh.library.Base.BaseFragment;
import com.yuyh.library.Base.util.RxTransUtil;
import com.yuyh.library.utils.DimenUtils;
import com.yuyh.library.utils.log.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import static com.exsun.meizi.base.MzApplication.cache;

/**
 * @author xiaokun
 * @date 2017/12/13
 */
@Keep
public class GuolinFragment extends BaseFragment
{
    private static final String GUO_LIN = "郭霖";
    public static final String TITLE = "question_link";
    public static final String TIME = "timestamp";
    public static final String HREF = "href";
    public static final String GUO_LIN_ARTICAL = "guo_lin_artical";
    public static final String GUO_LIN_TITLE = "guo_lin_title";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.edit_tx)
    EditText editTx;
    @Bind(R.id.line_divider)
    View lineDivider;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    private int page = 0;
    private Document document = null;
    private boolean mIsFirstTimeTouchBottom = true;
    public static final int PRELOAD_SIZE = 6;
    private List<BlogEntity> blogEntities = new ArrayList<>();
    private BlogAdapter adapter;
    private StaggeredGridLayoutManager manager;

    public static GuolinFragment getInstance()
    {
        GuolinFragment mGuolinFragment = new GuolinFragment();
        return mGuolinFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_guolin;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initData(Bundle bundle)
    {
    }

    @Override
    public void initView(Bundle savedInstanceState, View view)
    {
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
            }
        });
        toolbar.setTitle("郭霖公众号");
        //监听editText  软键盘回车键
        editTx.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    fuzzySearch();
                }
                return false;
            }
        });

//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
//        {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
//            {
//                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange())
//                {
//                    lineDivider.setVisibility(View.VISIBLE);
//                } else
//                {
//                    lineDivider.setVisibility(View.GONE);
//                }
//            }
//        });

    }

    private void fuzzySearch()
    {
        String trim = editTx.getText().toString().trim();
        if (TextUtils.isEmpty(trim))
        {
            Toasts.showSingleShort("模糊搜素不能为空");
        } else
        {
            List<BlogEntity> cacheBlogs = (List<BlogEntity>) MzApplication.cache.getAsObject(GUO_LIN_ARTICAL);
            List<BlogEntity> fuzzyDatas = new ArrayList<>();
            int size = cacheBlogs.size();
            for (int i = 0; i < size; i++)
            {
                BlogEntity blogEntity = cacheBlogs.get(i);
                String desc = blogEntity.getTitle();
                String publishedAt = blogEntity.getTime();
                String who = blogEntity.getAuthor();
                if (desc == null)
                {
                    desc = "";
                }
                if (publishedAt == null)
                {
                    publishedAt = "";
                }
                if (who == null)
                {
                    who = "";
                }
                if (desc.contains(trim) || publishedAt.contains(trim) || who.contains(trim))
                {
                    fuzzyDatas.add(blogEntity);
                }
            }
            blogEntities.clear();
            blogEntities.addAll(fuzzyDatas);
            recyclerView.addOnScrollListener(getOnButtomListener(manager, false));
            adapter.setNewData(blogEntities);
        }
    }

    @Override
    public void doBusiness(Context context)
    {
        recyclerView = utils.initRv(recyclerView, 1, DimenUtils.dpToPxInt(2), StaggeredGridLayoutManager.VERTICAL);
        manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        adapter = new BlogAdapter(context, R.layout.item_other, blogEntities);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                page = 0;
                getDocument(page, true);
            }
        });
//        MzApplication.cache.put(GUO_LIN_ARTICAL, new ArrayList<BlogEntity>());
        getDocument(page, false);
    }

    private void getDocument(final int page, final boolean isClearData)
    {
        swipeRefreshLayout.setRefreshing(true);
        Observable.create(new ObservableOnSubscribe<List<BlogEntity>>()
        {
            @Override
            public void subscribe(ObservableEmitter<List<BlogEntity>> e) throws Exception
            {
                try
                {
                    String url = Constant.GUOLIN_BASE_URL + "?start=" + 12 * page;
                    document = Jsoup.connect(url).get();
                    blogEntities = parseDocument(document, isClearData);
                    e.onNext(blogEntities);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                    e.onError(ex);
                }
            }
        }).compose(RxTransUtil.<List<BlogEntity>>rxSchedulerHelper())
                .subscribe(new Consumer<List<BlogEntity>>()
                {
                    @Override
                    public void accept(List<BlogEntity> blogEntities) throws Exception
                    {
                        swipeRefreshLayout.setRefreshing(false);
                        recyclerView.addOnScrollListener(getOnButtomListener(manager, true));
                        adapter.setNewData(blogEntities);
                    }
                }, new Consumer<Throwable>()
                {
                    @Override
                    public void accept(Throwable throwable) throws Exception
                    {
                        Toasts.showSingleShort(throwable.getMessage());
                    }
                });
    }

    private List<BlogEntity> parseDocument(Document document, boolean isClearData)
    {
        long l = System.currentTimeMillis();
        if (isClearData)
        {
            blogEntities.clear();
        }
        List<Element> titleElementList = new ArrayList<>();
        List<Element> timeElementList = new ArrayList<>();
        Elements titles = document.getElementsByClass(TITLE);
        for (Element element : titles)
        {
            titleElementList.add(element);
        }

        Elements times = document.getElementsByClass(TIME);
        for (Element element : times)
        {
            timeElementList.add(element);
        }

        List<BlogEntity> cacheBlogs = (List<BlogEntity>) MzApplication.cache.getAsObject(GUO_LIN_ARTICAL);
        List<String> cacheTitles = (List<String>) MzApplication.cache.getAsObject(GUO_LIN_TITLE);
        if (cacheBlogs == null)
        {
            cacheBlogs = new ArrayList<>();
        }
        if (cacheTitles == null)
        {
            cacheTitles = new ArrayList<>();
        }
        int size = timeElementList.size();
        List<BlogEntity> newBlogs = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setAuthor(GUO_LIN);
            blogEntity.setTime(timeElementList.get(i).text());
            blogEntity.setTitle(titleElementList.get(i).text());
            blogEntity.setUrl(Constant.CSM_BASE_URL + titleElementList.get(i).attr(HREF));
            blogEntities.add(blogEntity);
            if (!cacheTitles.toString().contains(blogEntity.getTitle()))
            {
                newBlogs.add(blogEntity);
            }
        }
        cacheBlogs.addAll(newBlogs);
        cache.put(GUO_LIN_ARTICAL, (Serializable) cacheBlogs);
        for (BlogEntity blogEntity : newBlogs)
        {
            cacheTitles.add(blogEntity.getTitle());
        }
        cache.put(GUO_LIN_TITLE, (Serializable) cacheTitles);
        LogUtils.e("yixun-xk", "parseDocument()" + (System.currentTimeMillis() - l));
        return blogEntities;
    }

    private RecyclerView.OnScrollListener getOnButtomListener(final StaggeredGridLayoutManager layoutManager, final boolean autoLoad)
    {
        return new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if (autoLoad)
                {
                    int[] positions = layoutManager.findLastCompletelyVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                    boolean isBottom = positions[layoutManager.getSpanCount() - 1] >= (adapter.getItemCount() - PRELOAD_SIZE);
                    if (!swipeRefreshLayout.isRefreshing() && isBottom)
                    {
                        if (!mIsFirstTimeTouchBottom)
                        {
                            page++;
                            swipeRefreshLayout.setRefreshing(true);
                            getDocument(page, false);
                        } else
                        {
                            mIsFirstTimeTouchBottom = false;
                        }
                    }
                }
            }
        };
    }
}
