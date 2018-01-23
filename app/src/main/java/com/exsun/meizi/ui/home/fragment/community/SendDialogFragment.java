package com.exsun.meizi.ui.home.fragment.community;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.exsun.meizi.R;
import com.exsun.meizi.entity.bmob.MyUser;
import com.exsun.meizi.entity.bmob.TalkMoodEntity;
import com.exsun.meizi.tool.Toasts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *     作者   : 肖坤
 *     时间   : 2018/01/23
 *     描述   :
 *     版本   : 1.0
 * </pre>
 */

public class SendDialogFragment extends DialogFragment
{
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private MyUser user;


    public SendDialogFragment()
    {

    }

    public static SendDialogFragment getInstance(MyUser user)
    {
        SendDialogFragment mSendDialogFragment = new SendDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        mSendDialogFragment.setArguments(args);
        return mSendDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.send_talk_mood, null);
        return dialogView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        user = (MyUser) arguments.getSerializable("user");
        final EditText editText = (EditText) view.findViewById(R.id.edit_text);
        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //fasong
                String content = editText.getText().toString().trim();
                if (TextUtils.isEmpty(content))
                {
                    Toasts.showSingleShort("内容不能为空");
                    return;
                }
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
                ConfirmDialogListener listener = (ConfirmDialogListener) getTargetFragment();
                listener.confirm(talkMoodEntity);
            }
        });
    }

    public interface ConfirmDialogListener
    {
        void confirm(TalkMoodEntity talkMoodEntity);
    }

}
