package com.exsun.meizi.ui.search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.gank.SearchEntity;
import com.exsun.meizi.tool.ImageLoaderUtils;
import com.exsun.meizi.ui.picture.PictureActivity;
import com.exsun.meizi.widget.OffsetDecoration;
import com.exsun.meizi.widget.WordWrapView;
import com.yuyh.library.Base.BaseBackActicity;
import com.yuyh.library.utils.DimenUtils;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/12.
 */

public class GankSearchActivity extends BaseBackActicity<GankSearchPresenter, GankSearchModel>
        implements GankSearchContract.View
{
    private static final String SEARCH_HISTORY = "search_history";
    @Bind(R.id.search_edit)
    EditText searchEdit;
    @Bind(R.id.search_toobar)
    Toolbar searchToobar;
    @Bind(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;
    @Bind(R.id.search_swipe)
    SwipeRefreshLayout searchSwipe;
    @Bind(R.id.rl_history)
    LinearLayout linearLayout;
    @Bind(R.id.wv_search_history)
    WordWrapView wordWrapView;
    @Bind(R.id.img_search_clear)
    ImageView imageView;


    private int page = 1;
    private String trim;
    private String type = "Android";
    private int count = 20;

    public SearchAdapter adapter;
    private HeaderAndFooterWrapper wrapper;
    public List<SearchEntity.ResultsBean> datas;
    public static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private ImageView img;
    private boolean isClearData = true;
    private int IMAGE_HEIGHT = 200;//DP
    private List<HashMap<String, String>> searchHistory;

    @Override
    public void initData(Bundle bundle)
    {
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_gank_search;
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView()
    {
        searchToobar.setContentInsetStartWithNavigation(0);
        searchToobar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));
        searchToobar.inflateMenu(R.menu.menu_search);
        searchToobar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                back();
            }
        });

        searchToobar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.android_search:
                        type = "Android";
                        break;
                    case R.id.ios_search:
                        type = "IOS";
                        break;
                    case R.id.front_search:
                        type = "前端";
                        break;
                }
                trim = searchEdit.getText().toString().trim();
                editSearch(trim, type);
                return false;
            }
        });

        //监听editText  软键盘回车键
        searchEdit.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    type = "Android";
                    trim = searchEdit.getText().toString().trim();
                    editSearch(trim, type);
                }
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
    }

    private void back()
    {
        trim = searchEdit.getText().toString().trim();
        if (!TextUtils.isEmpty(trim))
        {
            searchEdit.setText("");
            linearLayout.setVisibility(View.VISIBLE);
            searchRecyclerView.setVisibility(View.GONE);
            addHistoryView();
        } else
        {
            finish();
        }
    }

    /**
     * 监听edittext  软键盘回车键
     *
     * @param trim
     * @param type
     */
    private void editSearch(String trim, String type)
    {
        if (!TextUtils.isEmpty(trim))
        {
            isClearData = true;
            searchSwipe.setRefreshing(true);
            mPresenter.getSearchData(trim, type, count, page);
            if (!searchHistory.toString().contains(trim))
            {
                HashMap<String, String> map = new HashMap<>();
                map.put(trim, type);
                searchHistory.add(map);
            }
            MzApplication.cache.put(SEARCH_HISTORY, (Serializable) searchHistory);
        } else
        {
            toastUtils.showSingleToast(R.string.please_input_something);
        }
        hideSoftInput();
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘 //
        imm.hideSoftInputFromWindow(searchEdit.getWindowToken(), 0);
        //显示软键盘
        //imm.showSoftInputFromInputMethod(searchEdit.getWindowToken(), 0);
        //切换软键盘的显示与隐藏
    }

    @Override
    public void doBusiness(Context context)
    {
        addHistoryView();

        searchSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                trim = searchEdit.getText().toString().trim();
                if (TextUtils.isEmpty(trim))
                {
                    searchSwipe.setRefreshing(false);
                    toastUtils.showSingleToast(R.string.please_input_something);
                    return;
                }
                isClearData = true;
                page = 1;
                mPresenter.getSearchData(trim, type, count, page);
            }
        });

        final int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        searchRecyclerView.addItemDecoration(new OffsetDecoration(spacing));
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.addOnScrollListener(getOnButtomListener(layoutManager));

        datas = new ArrayList<>();
        adapter = new SearchAdapter(this, R.layout.item_other, datas);
        wrapper = new HeaderAndFooterWrapper(adapter);
        View headView = View.inflate(this, R.layout.head_view_img, null);
        img = (ImageView) headView.findViewById(R.id.head_img);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PictureActivity.jumpToPictureActivity(GankSearchActivity.this, MzApplication.mPref.get(Constant.MY_LIKE_URL, ""),
                        "妹纸", img);
            }
        });
        wrapper.addHeaderView(img);
        searchRecyclerView.setAdapter(wrapper);
    }

    private void addHistoryView()
    {
        searchHistory = (List<HashMap<String, String>>) MzApplication.cache.getAsObject(SEARCH_HISTORY);
        if (searchHistory == null || searchHistory.isEmpty())
        {
            searchHistory = new ArrayList<>();
            linearLayout.setVisibility(View.GONE);
        } else
        {
            wordWrapView.removeAllViews();
            int size = searchHistory.size();
            for (int i = 0; i < size; i++)
            {
                final HashMap<String, String> map = searchHistory.get(i);
                Set<String> keys = map.keySet();
                for (final String key : keys)
                {
                    final String type = map.get(key);
                    final TextView textView = new TextView(GankSearchActivity.this);
                    textView.setTextColor(Color.parseColor("#efefef"));
                    textView.setText(key);
                    textView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            searchEdit.setText(key);
                            editSearch(key, type);
                        }
                    });

                    textView.setOnLongClickListener(new View.OnLongClickListener()
                    {
                        @Override
                        public boolean onLongClick(View v)
                        {
                            searchHistory.remove(map);
                            MzApplication.cache.put(SEARCH_HISTORY, (Serializable) searchHistory);
                            wordWrapView.removeView(textView);
                            return true;
                        }
                    });
                    wordWrapView.addView(textView);
                }

            }
        }
    }

    @Override
    public void getSearchSuccess(List<SearchEntity.ResultsBean> resultsBeanList)
    {
        searchRecyclerView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        String url = MzApplication.mPref.get(Constant.MY_LIKE_URL, "");
        ImageLoaderUtils.displaySize(this, img, url, DimenUtils.getScreenWidth()
                , (int) DimenUtils.dpToPx(IMAGE_HEIGHT));
        searchSwipe.setRefreshing(false);
        if (isClearData)
        {
            datas.clear();
        }
        datas.addAll(resultsBeanList);
        wrapper.notifyDataSetChanged();
        isClearData = false;
    }

    @Override
    public void getSearchFailed(Throwable throwable)
    {

    }

    @Override
    public void setStatusBar()
    {
        //        super.setStatusBar();
    }

    private RecyclerView.OnScrollListener getOnButtomListener(final StaggeredGridLayoutManager layoutManager)
    {
        return new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions = layoutManager.findLastCompletelyVisibleItemPositions(new int[layoutManager.getSpanCount()]);
                boolean isBottom = positions[layoutManager.getSpanCount() - 1] >= (adapter.getItemCount() - PRELOAD_SIZE);
                if (!searchSwipe.isRefreshing() && isBottom)
                {
                    if (!mIsFirstTimeTouchBottom)
                    {
                        page++;
                        searchSwipe.setRefreshing(true);
                        mPresenter.getSearchData(trim, type, count, page);
                    } else
                    {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    @OnClick(R.id.img_search_clear)
    public void onViewClicked()
    {
        if (searchHistory != null)
        {
            searchHistory.clear();
            MzApplication.cache.put(SEARCH_HISTORY, (Serializable) searchHistory);
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed()
    {
        back();
    }
}
