package com.zyyoona7.myloadingviews.progress;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.zyyoona7.myloadingviews.BaseActivity;
import com.zyyoona7.myloadingviews.R;
import com.zyyoona7.myloadingviews.progress.adapter.RecyclerAdapter;
import com.zyyoona7.myloadingviews.progress.header.IndicatorHeaderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/6/20.
 */

public abstract class BaseProgressActivity extends BaseActivity {
    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_indicator;
    }

    @Override
    protected void initVariables() {
        mAdapter = new RecyclerAdapter();
        List<String> list = new ArrayList<>(1);
        for (int i = 0; i < 15; i++) {
            list.add("Just Do IT");
        }
        mAdapter.setNewData(list);
    }

    @Override
    protected void initViews() {
        mRefreshLayout = (TwinklingRefreshLayout) findViewById(R.id.tfl_indicator);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_indicator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRefreshLayout.setHeaderView(setHeaderView());
    }

    @Override
    protected void initEvents() {
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

        });
    }

    /**
     * 设置HeaderView
     *
     * @return
     */
    protected abstract IHeaderView setHeaderView();
}
