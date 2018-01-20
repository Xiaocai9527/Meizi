package com.exsun.meizi.ui.dummy;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author xiaokun
 * @date 2017/12/18
 */

public class DummyActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
            }
        }, 500);
    }
}
