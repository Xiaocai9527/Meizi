package com.exsun.meizi.ui.home.fragment.like;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.entity.gank.MyLikeEntity;
import com.exsun.meizi.entity.gank.RadomMzEntity;
import com.exsun.meizi.helper.ImageLoaderUtils;
import com.exsun.meizi.helper.Toasts;
import com.exsun.meizi.network.Api;
import com.exsun.meizi.network.ApiService;
import com.exsun.meizi.ui.home.activity.LoginActivity;
import com.exsun.meizi.ui.picture.PictureActivity;
import com.exsun.meizi.ui.web.BaseWebActivity;
import com.exsun.meizi.widget.OffsetDecoration;
import com.yuyh.library.Base.BaseActivity;
import com.yuyh.library.Base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiaokun on 2017/8/8.
 */
@Keep
public class LikeFragment extends BaseFragment
{
    @Bind(R.id.like_rv)
    RecyclerView recyclerView;

    @Bind(R.id.like_refresh)
    SwipeRefreshLayout refresh;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.empty_tv)
    TextView emptyTv;

    private CommonAdapter<MyLikeEntity> adapter;

    public static LikeFragment getInstance()
    {
        LikeFragment mLikeFragment = new LikeFragment();
        return mLikeFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_like;
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
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refresh();
            }
        });
        toolbar.setTitle("我的收藏");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setTitleTextColor(Color.parseColor("#efefef"));
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.more));
        toolbar.inflateMenu(R.menu.menu_like);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.clear_cache:
//                        List<MyLikeEntity> myLikeEntities = new ArrayList<>();
//                        MzApplication.cache.put(Constant.MY_LIKE_DATA, (Serializable) myLikeEntities);
//                        if (myLikeEntities != null && !myLikeEntities.isEmpty())
//                        {
//                            int size = myLikeEntities.size();
//                            for (int i = 0; i < size; i++)
//                            {
//                                MyLikeEntity myLikeEntity = myLikeEntities.get(i);
//                                String url = myLikeEntity.getUrl();
//                                MzApplication.mPref.put(url, false);
//                            }
//                        }
//                        refresh();
//                        Toasts.showSingleShort(R.string.clear_success);
                        Toasts.showSingleLong("敬请期待");
                        break;
                    case R.id.action_change_skin:
                        Toasts.showSingleShort("请在侧边栏操作,谢谢!");
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }

//    private List<MyLikeEntity> myLikeEntities;

//    private List<BmobObject> collections = new ArrayList<>();

    @Override
    public void doBusiness(final Context context)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        final int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_2_dp);
        recyclerView.addItemDecoration(new OffsetDecoration(spacing));
        EventBus.getDefault().register(this);
        refresh.setRefreshing(true);
        loadData(true);
        setAdapter(myLikeEntities);
    }

    private void loadData(final boolean isClear)
    {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyLikeEntity> query = new BmobQuery<>();
        if (user == null)
        {
            //未登录
            setNoLogin();
            return;
        }
        query.setLimit(500);
        query.addWhereEqualTo("myUser", user);  // 查询当前用户的所有帖子
        query.findObjects(new FindListener<MyLikeEntity>()
        {
            @Override
            public void done(List<MyLikeEntity> list, BmobException e)
            {
                if (e == null)
                {
                    Toasts.showSingleShort("查询成功：共" + list.size() + "条数据。");
                    getDataSuccess(list, isClear);
                } else
                {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void setNoLogin()
    {
        refresh.setVisibility(View.GONE);
        emptyTv.setVisibility(View.VISIBLE);
        emptyTv.setText("你还未登录,请先登录");
        emptyTv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
    }

    private List<MyLikeEntity> myLikeEntities = new ArrayList<>();

    /**
     * 获取数据成功
     *
     * @param list
     */
    private void getDataSuccess(List<MyLikeEntity> list, boolean isClear)
    {
        Log.e("likeFragment", "getDataSuccess");
        savePref(list);
        refresh.setRefreshing(false);
        if (isClear)
        {
            myLikeEntities.clear();
        }
        if (list.isEmpty())
        {
            refresh.setVisibility(View.GONE);
            emptyTv.setVisibility(View.VISIBLE);
            emptyTv.setText("空空如也\n\n你还没有收藏任何文章");
        } else
        {
            refresh.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            myLikeEntities.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    private void savePref(List<MyLikeEntity> list)
    {
        int size = list.size();
        for (int i = 0; i < size; i++)
        {
            MyLikeEntity myLikeEntity = list.get(i);
            MzApplication.mPref.put(myLikeEntity.getUrl(), true);
            MzApplication.mPref.put(myLikeEntity.getUrl() + "id", myLikeEntity.getObjectId());
        }
    }

    /**
     * 上传收藏数据到服务器
     *
     * @param myLikeEntity
     */
    private void saveData(final MyLikeEntity myLikeEntity)
    {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        myLikeEntity.setMyUser(user);
        myLikeEntity.save(new SaveListener<String>()
        {
            @Override
            public void done(String s, BmobException e)
            {
                if (e == null)
                {
                    MzApplication.mPref.put(myLikeEntity.getUrl(), true);
                    MzApplication.mPref.put(myLikeEntity.getUrl() + "id", s);
                    Log.e("objectId", s);
                    Toasts.showSingleShort("收藏成功");
                } else
                {
                    Toasts.showSingleShort("收藏失败");
                }
            }
        });
    }

    /**
     * 刷新
     */
    private void refresh()
    {
        refresh.setRefreshing(true);
        loadData(true);
    }

    /**
     * 设置adpater
     *
     * @param myLikeEntities 数据源
     */
    private void setAdapter(List<MyLikeEntity> myLikeEntities)
    {
        adapter = new CommonAdapter<MyLikeEntity>(mActivity, R.layout.item_my_like, myLikeEntities)
        {
            @Override
            protected void convert(final ViewHolder holder, final MyLikeEntity myLikeEntity, int position)
            {
                ApiService apiService = Api.getDefault(0);
                apiService.getRadomMz("福利", 1).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RadomMzEntity>()
                        {
                            @Override
                            public void onSubscribe(Disposable d)
                            {

                            }

                            @Override
                            public void onNext(RadomMzEntity value)
                            {
                                final RadomMzEntity.ResultsBean resultsBean = value.getResults().get(0);
                                final String imgUrl = resultsBean.getUrl();
                                final ImageView imgView = holder.getView(R.id.img_like);
                                ImageLoaderUtils.display(mActivity, imgView, imgUrl);

                                imgView.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        PictureActivity.jumpToPictureActivity((BaseActivity) mContext, imgUrl, resultsBean.getDesc(), imgView);
                                    }
                                });
                            }

                            @Override
                            public void onError(Throwable e)
                            {

                            }

                            @Override
                            public void onComplete()
                            {

                            }
                        });

                TextView tvDesc = holder.getView(R.id.my_like_desc);
                tvDesc.setText(myLikeEntity.getDesc());

                TextView tvAuthor = holder.getView(R.id.author_like);
                tvAuthor.setText("作者：" + myLikeEntity.getAuthor());

                holder.setOnClickListener(R.id.like_cv, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(mContext, BaseWebActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(BaseWebActivity.WEB_URL, myLikeEntity.getUrl());
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(MyLikeEntity myLikeEntity)
    {
        Log.e("likeFragment", "onMessage");
        if (myLikeEntity.isCancel())
        {
            deleteData(myLikeEntity);
        } else
        {
            refresh.setVisibility(View.VISIBLE);
            emptyTv.setVisibility(View.GONE);
            myLikeEntities.add(myLikeEntity);
            adapter.notifyDataSetChanged();
            saveData(myLikeEntity);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MyUser myUser)
    {
        loadData(true);
    }

    /**
     * 删除数据
     *
     * @param myLikeEntity
     */
    private void deleteData(MyLikeEntity myLikeEntity)
    {
        MyLikeEntity entity = new MyLikeEntity();
        entity.remove("myUser");
        entity.update(myLikeEntity.getObjectId(), new UpdateListener()
        {
            @Override
            public void done(BmobException e)
            {
                if (e == null)
                {
                    Toasts.showSingleShort("取消收藏");
                    loadData(true);
                } else
                {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
