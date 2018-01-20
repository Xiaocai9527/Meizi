package com.yuyh.library.Base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;
import android.view.Window;

import com.yuyh.library.Base.util.TUtil;
import com.yuyh.library.R;
import com.yuyh.library.swipeback.SwipeBackHelper;
import com.yuyh.library.utils.AppManager;
import com.yuyh.library.utils.DayNightModelUtils.ChangeModeController;
import com.yuyh.library.utils.StatusBarUtil;
import com.yuyh.library.utils.toast.ToastUtils;

import butterknife.ButterKnife;

/**
 * @author xiaokun
 * @date 2017/12/25
 */
@Keep
public abstract class BaseBackActicity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity implements BaseView
{

    public Context mContext;
    public P mPresenter;
    public M mModel;
    public ToastUtils toastUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
//        ChangeModeController.getInstance().init(this, getClass());
        super.onCreate(savedInstanceState);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null)
        {
            mPresenter.mContext = this;
        }
        setStatusBar();
        this.initPresenter();
        this.initView();
        this.doBusiness(this);
    }

    /**
     * 初始化数据
     *
     * @param bundle 从上个Activity传递过来的bundle
     */
    public abstract void initData(Bundle bundle);

    /**
     * 设置layout前配置
     */
    public void doBeforeSetcontentView()
    {
        //设置昼夜主题
        initTheme();
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //添加滑动返回
        addSlideBack();
        toastUtils = new ToastUtils();
    }

    private void addSlideBack()
    {
        SwipeBackHelper.onCreate(this);

        int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeEdge(scaledTouchSlop * 2)
                .setSwipeSensitivity(1.0f)
                .setSwipeSensitivity(1.0f)
                .setSwipeRelateEnable(false);//是否与下一级activity联动(微信效果)
//                .setScrimColor(Color.TRANSPARENT);//底层阴影颜色;
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化presenter层
     */
    protected abstract void initPresenter();

    /**
     * 初始化view
     */
    public abstract void initView();

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    /**
     * 业务操作
     *
     * @param context 上下文
     */
    public abstract void doBusiness(Context context);

    public void setStatusBar()
    {
        StatusBarUtil.setTranslucent(this, StatusBarUtil.ENVIOR_DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置主题
     */
    public void initTheme()
    {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls)
    {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode)
    {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * @param cls    将要跳转到的页面
     * @param bundle bundle包含有传递的参数
     */
    public void startActivity(Class<?> cls, Bundle bundle)
    {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ActivityCompat.startActivity(this, intent, null);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ButterKnife.unbind(this);
        SwipeBackHelper.onDestroy(this);
        if (mPresenter != null)
        {
            mPresenter.detachVM();
        }
        AppManager.getAppManager().finishActivity(this);
    }


}
