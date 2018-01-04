package com.yuyh.library.Base.rx;

import java.io.IOException;

/**
 * @author xiaokun
 * @date 2017/12/26
 */

public class ServerException extends IOException
{
    private int code;
    private String msg;

    public ServerException(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
}
