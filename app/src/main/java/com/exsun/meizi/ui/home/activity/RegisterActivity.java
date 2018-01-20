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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.MyUser;
import com.exsun.meizi.helper.Toasts;
import com.yuyh.library.Base.BaseBackActicity;

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

public class RegisterActivity extends BaseBackActicity
{
    public static final String REGISTER_USERNAME = "register_username";
    public static final String REGISTER_PASSWORD = "register_password";

    public static final String REGISTER_NICKNAME = "register_nickname";
    public static final String REGISTER_LOCATION = "register_location";

    @Bind(R.id.tv_nick_name)
    AutoCompleteTextView tvNickName;
    @Bind(R.id.input_nick_name)
    TextInputLayout inputNickName;
    @Bind(R.id.tv_user_name)
    AutoCompleteTextView tvUserName;
    @Bind(R.id.input_user_name)
    TextInputLayout inputUserName;
    @Bind(R.id.tv_password)
    EditText tvPassword;
    @Bind(R.id.input_password)
    TextInputLayout inputPassword;
    @Bind(R.id.tv_password_again)
    EditText tvPasswordAgain;
    @Bind(R.id.input_password_again)
    TextInputLayout inputPasswordAgain;
    @Bind(R.id.tv_location_city)
    EditText tvLactionCity;
    @Bind(R.id.input_location_city)
    TextInputLayout inputLocationCity;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.frame_layout)
    FrameLayout frameLayout;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String nickName;
    private String userName;
    private String password;
    private String passwordAgain;
    private String location;

    @Override
    public void initData(Bundle bundle)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter()
    {

    }

    @Override
    public void initView()
    {
        initToolbar();
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

    @OnClick(R.id.frame_layout)
    public void onViewClicked()
    {
        attemptRegister();
    }

    private void attemptRegister()
    {
        inputUserName.setError(null);
        inputPassword.setError(null);

        nickName = tvNickName.getText().toString();
        userName = tvUserName.getText().toString();
        password = tvPassword.getText().toString();
        passwordAgain = tvPasswordAgain.getText().toString();
        location = tvLactionCity.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(nickName))
        {
            inputNickName.setError("请输入昵称");
            focusView = tvNickName;
            cancel = true;
        } else if (TextUtils.isEmpty(userName))
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
        } else if (!passwordAgain.equals(password))
        {
            inputPasswordAgain.setError("两次输入密码不一致");
            focusView = tvPasswordAgain;
            cancel = true;
        } else if (TextUtils.isEmpty(location))
        {
            inputLocationCity.setError("请输入所在城市");
            focusView = tvLactionCity;
            cancel = true;
        }

        if (cancel)
        {
            focusView.requestFocus();
        } else
        {
            tvLogin.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            executeRegister();
        }
    }

    private void executeRegister()
    {
        MyUser myUser = new MyUser();
        myUser.setUsername(userName);
        myUser.setNickName(nickName);
        myUser.setPassword(password);
        myUser.setLocation(location);

        myUser.signUp(new SaveListener<MyUser>()
        {
            @Override
            public void done(MyUser myUser, BmobException e)
            {
                progressBar.setVisibility(View.GONE);
                tvLogin.setVisibility(View.VISIBLE);
                if (e == null)
                {
                    Toasts.showSingleShort("注册成功:");
                    Intent intent = new Intent();
                    intent.putExtra(RegisterActivity.REGISTER_USERNAME, userName);
                    intent.putExtra(RegisterActivity.REGISTER_PASSWORD, password);
                    intent.putExtra(RegisterActivity.REGISTER_NICKNAME, nickName);
                    intent.putExtra(RegisterActivity.REGISTER_LOCATION, location);

                    setResult(RESULT_OK, intent);
                    finish();
                } else
                {
                    Toasts.showSingleShort("注册失败：" + e.getMessage());
                    tvLogin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Log.e("RegisterActivity", "done(RegisterActivity.java:207)" + e.getMessage());
                }
            }
        });
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

}
