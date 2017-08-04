package com.exsun.meizi.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import com.exsun.meizi.R;
import com.exsun.meizi.config.Constant;
import com.exsun.meizi.entity.HomeMixEntity;
import com.exsun.meizi.ui.adapter.MzRvAdapter;
import com.exsun.meizi.ui.fragment.meizi.MeiziContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaokun on 2017/8/2.
 */

public class MzFragment extends GankFragment implements MeiziContract.View
{
    private MzRvAdapter adapter;
    private List<HomeMixEntity> datas;

    public static MzFragment getInstance(Bundle bundle)
    {
        MzFragment fragment = new MzFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getDataFromDBSuccess(List<HomeMixEntity> homeMixEntities)
    {
        datas = new ArrayList<>();
        datas.addAll(homeMixEntities);
        adapter = new MzRvAdapter(mActivity, R.layout.item_category, datas);
        homeRv.setAdapter(adapter);
        getData(Constant.WELFARE, Constant.VIDEO, count, page);
    }

    @Override
    public void getMixSuccess(List<HomeMixEntity> homeMixEntities)
    {
        homeSr.setRefreshing(false);
        if (isClearData)
        {
            datas.clear();
        }
        datas.addAll(homeMixEntities);
        adapter.notifyDataSetChanged();
        isClearData = false;
    }

    @Override
    public void initQuery()
    {
        query1 = Constant.WELFARE;
        query2 = Constant.VIDEO;
    }

    @Override
    public void getData(String query1, String query2, int count, int page)
    {
        mPresenter.getMixData(query1, query2, count, page);
    }

    @Override
    protected void initPresenter()
    {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void doBusiness(Context context)
    {
        mPresenter.getMixDataFormDB();
    }
}
