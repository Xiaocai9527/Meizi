package com.yuyh.library.Base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 肖坤 on 2017/12/9.
 *
 * @author 肖坤
 * @date 2017/12/9
 */

public class BaseResponse
{
    public static final int CODE_SUCCESS = 0;

    public String msg;
    public int code;
    @SerializedName("error_response")
    public ErrorResponse errorResponse;

    public static int getCodeSuccess()
    {
        return CODE_SUCCESS;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public ErrorResponse getErrorResponse()
    {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse)
    {
        this.errorResponse = errorResponse;
    }

    public static final class ErrorResponse
    {
        public String msg;
        public int code;

        public String getMsg()
        {
            return msg;
        }

        public void setMsg(String msg)
        {
            this.msg = msg;
        }

        public int getCode()
        {
            return code;
        }

        public void setCode(int code)
        {
            this.code = code;
        }
    }
}
