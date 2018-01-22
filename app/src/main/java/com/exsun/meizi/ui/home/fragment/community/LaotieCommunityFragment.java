package com.exsun.meizi.ui.home.fragment.community;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.tool.Toasts;
import com.exsun.meizi.ui.home.activity.LoginActivity;
import com.yuyh.library.Base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class LaotieCommunityFragment extends BaseFragment
{
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout frameLayout;
    private MyUser user;
    private BottomSheetDialog dialog;
    private CircleFragment circleFragment;

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
                            startActivity(LoginActivity.class);
                            Toasts.showSingleShort("请先登录");
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
        dialog = new BottomSheetDialog(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.send_talk_mood, null);
        final EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
        Button btnOk = (Button) dialogView.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        dialog.setContentView(dialogView);
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //fasong
                String content = editText.getText().toString().trim();
                long time = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                String publishedTime = sdf.format(new Date(time));
                String location = user.getLocation();
                TalkMoodEntity talkMoodEntity = new TalkMoodEntity();
                talkMoodEntity.setContent(content);
                talkMoodEntity.setLocation(location);
                talkMoodEntity.setPublishedTime(publishedTime);
                talkMoodEntity.setUser(user);
                talkMoodEntity.setNickName(user.getNickName());
                saveData(talkMoodEntity);
            }
        });
        dialog.show();
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
                dialog.dismiss();
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
}
