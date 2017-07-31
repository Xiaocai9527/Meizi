package com.exsun.meizi.ui.base;

import com.exsun.meizi.network.Api;
import com.exsun.meizi.network.ApiService;
import com.yuyh.library.Base.BaseModel;

/**
 * Created by xiaokun on 2017/7/26.
 */

public interface AppBaseModel extends BaseModel
{
    ApiService apiService = Api.getDefault(Api.RELEASE);
}
