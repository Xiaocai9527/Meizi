package com.exsun.meizi.ui.home.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.entity.eventbus.EventBusObject;
import com.exsun.meizi.tool.Toasts;
import com.exsun.meizi.ui.home.activity.LoginActivity;
import com.yuyh.library.Base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 肖坤 on 2018/1/20.
 * <p>
 * 老铁社区
 *
 * @author 肖坤
 * @date 2018/1/20
 */

public class LaotieCommunityFragment extends BaseFragment implements SendDialogFragment.ConfirmDialogListener
{
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout frameLayout;
    @Bind(R.id.editTextBodyLl)
    LinearLayout linearLayout;

    private MyUser user;
    private CircleFragment circleFragment;
    private SendDialogFragment fragment;

    public static LaotieCommunityFragment getInstance()
    {
        LaotieCommunityFragment mCommunityFragment = new LaotieCommunityFragment();
        return mCommunityFragment;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_laotie_community;
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
        EventBus.getDefault().register(this);
        initToolbar();
        initFragment();
    }

    private void initToolbar()
    {
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setTitleTextColor(Color.parseColor("#efefef"));
        toolbar.inflateMenu(R.menu.menu_community);
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
                    case R.id.send_talk:
                        user = BmobUser.getCurrentUser(MyUser.class);
                        if (user == null)
                        {
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            Toasts.showSingleShort("请先登录");
                            break;
                        }
                        //发表动态
                        sendTalkMood();
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }

    private void initFragment()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (circleFragment == null)
        {
            circleFragment = CircleFragment.getInstance();
        }
        ft.add(R.id.container, circleFragment, "circle").show(circleFragment).commit();
    }

    private void sendTalkMood()
    {
        FragmentManager fm = getChildFragmentManager();
        fragment = SendDialogFragment.getInstance(user);
        fragment.setTargetFragment(LaotieCommunityFragment.this, 0);
        fragment.show(fm, "send_dialog_fragment");
    }

    /**
     * 上传动态到服务器
     *
     * @param talkMoodEntity
     */
    private void saveData(TalkMoodEntity talkMoodEntity)
    {
        talkMoodEntity.save(new SaveListener<String>()
        {
            @Override
            public void done(String s, BmobException e)
            {
                fragment.dismiss();
                if (e == null)
                {
                    Toasts.showSingleShort("发表成功");
                    circleFragment.loadData(true);
                } else
                {
                    Toasts.showSingleShort("发表失败" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {
    }

    @Override
    public void confirm(TalkMoodEntity talkMoodEntity)
    {
        saveData(talkMoodEntity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventBusObject.OpenEditObject openEditObject)
    {
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
