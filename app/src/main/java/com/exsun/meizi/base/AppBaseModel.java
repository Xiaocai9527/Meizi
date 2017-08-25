package com.exsun.meizi.base;

import com.exsun.meizi.network.Api;
import com.exsun.meizi.network.ApiService;
import com.exsun.meizi.network.DouyuApiService;
import com.yuyh.library.Base.BaseModel;

/**
 * Created by xiaokun on 2017/7/26.
 */

public interface AppBaseModel extends BaseModel
{
    ApiService apiService = Api.getDefault(Api.GANK);

    DouyuApiService dyService = Api.getDouyuService(Api.DOUYU);

}
