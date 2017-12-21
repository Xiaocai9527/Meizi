package com.yuyh.library.Base;

import android.support.annotation.Keep;

/**
 * @author xiaokun
 * @date 2017/12/12
 */
@Keep
public class BaseEntity<T>
{
    private int code;
    private String msg;
    private T result;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }
}
