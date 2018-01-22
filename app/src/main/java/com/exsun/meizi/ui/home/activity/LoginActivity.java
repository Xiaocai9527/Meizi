package com.exsun.meizi.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.base.MzApplication;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.tool.Toasts;
import com.yuyh.library.Base.BaseBackActicity;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/19
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class LoginActivity extends BaseBackActicity
{
    private static final int REGISTER_REQUEST = 0;
    @Bind(R.id.tv_user_name)
    AutoCompleteTextView tvUserName;
    @Bind(R.id.input_user_name)
    TextInputLayout inputUserName;
    @Bind(R.id.tv_password)
    EditText tvPassword;
    @Bind(R.id.input_password)
    TextInputLayout inputPassword;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;
    @Bind(R.id.btn_forgot_password)
    Button btnForgotPassword;
    @Bind(R.id.btn_forgot_register)
    Button btnForgotRegister;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String userName;
    private String password;
    private String nickName;
    private String location;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        initToolbar();
        String nameFromSp = MzApplication.getCustomPref("user_name").get(Constant.APP_URSERNAME, "");
        if (!TextUtils.isEmpty(nameFromSp))
        {
            tvUserName.setText(nameFromSp);
        }
    }

    private void initToolbar()
    {
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    @Override
    public void doBusiness(Context context)
    {

    }

    private void attemptLogin()
    {
        inputUserName.setError(null);
        inputPassword.setError(null);

        userName = tvUserName.getText().toString();
        password = tvPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(userName))
        {
            inputUserName.setError("请输入用户名");
            focusView = tvUserName;
            cancel = true;
        } else if (!verifyLetOrNum(userName))
        {
            inputUserName.setError("用户名只能是数字字母组合");
            focusView = tvUserName;
            cancel = true;
        } else if (TextUtils.isEmpty(password))
        {
            inputPassword.setError("请输入密码");
            focusView = tvPassword;
            cancel = true;
        } else if (!verifyLetOrNum(password))
        {
            inputPassword.setError("密码只能是数字字母组合");
            focusView = tvPassword;
            cancel = true;
        }

        if (cancel)
        {
            focusView.requestFocus();
        } else
        {
            tvLogin.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            executeLogin();
        }
    }

    private static Pattern compile = Pattern.compile("^[A-Za-z0-9]+$");

    /**
     * 验证字母或数字
     *
     * @param value
     * @return
     */
    private boolean verifyLetOrNum(String value)
    {
        Matcher matcher = compile.matcher(value);
        return matcher.matches();
    }

    private void executeLogin()
    {
        MyUser myUser = new MyUser();
        myUser.setUsername(userName);
        myUser.setPassword(password);
        myUser.login(new SaveListener<MyUser>()
        {
            @Override
            public void done(MyUser myUser, BmobException e)
            {
                if (e == null)
                {
                    Toasts.showSingleShort("登录成功:");
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    MzApplication.mPref.put(Constant.IS_LOGIN, true);
                    MzApplication.mPref.put(Constant.APP_URSERNAME, myUser.getUsername());
                    MzApplication.mPref.put(Constant.APP_PASSWORD, "jiami");
                    MzApplication.mPref.put(Constant.APP_LOCATION, myUser.getLocation());
                    MzApplication.mPref.put(Constant.APP_NICKNAME, myUser.getNickName());
                    MzApplication.getCustomPref("user_name").put(Constant.APP_URSERNAME, myUser.getUsername());
                    EventBus.getDefault().post(myUser);
                    finish();
                } else
                {
                    Toasts.showLong("登录失败" + e.getMessage());
                    tvLogin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Log.e("LoginActivity", "done(LoginActivity.java:187)" + e.getMessage());
                }
            }
        });
    }

    @OnClick({R.id.frame_layout, R.id.btn_forgot_password, R.id.btn_forgot_register})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.frame_layout:
                attemptLogin();
                break;
            case R.id.btn_forgot_password:
                Toasts.showSingleShort("抱歉无能为力");
                break;
            case R.id.btn_forgot_register:
                startActivityForResult(RegisterActivity.class, REGISTER_REQUEST);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
        {
            return;
        }
        switch (requestCode)
        {
            case REGISTER_REQUEST:
                String userName = data.getStringExtra(RegisterActivity.REGISTER_USERNAME);
                String password = data.getStringExtra(RegisterActivity.REGISTER_PASSWORD);
                nickName = data.getStringExtra(RegisterActivity.REGISTER_NICKNAME);
                location = data.getStringExtra(RegisterActivity.REGISTER_LOCATION);
                tvUserName.setText(userName);
                tvPassword.setText(password);
                break;
            default:

                break;
        }
    }
}
