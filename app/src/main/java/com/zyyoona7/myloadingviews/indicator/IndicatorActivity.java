package com.zyyoona7.myloadingviews.indicator;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.zyyoona7.myloadingviews.BaseActivity;
import com.zyyoona7.myloadingviews.R;
import com.zyyoona7.myloadingviews.indicator.adapter.IndicatorAdapter;
import com.zyyoona7.myloadingviews.indicator.header.IndicatorHeaderView;

import java.util.ArrayList;
import java.util.List;

public class IndicatorActivity extends BaseActivity {

    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private IndicatorAdapter mAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_indicator;
    }

    @Override
    protected void initVariables() {
        mAdapter = new IndicatorAdapter();
        List<String> list = new ArrayList<>(1);
        for (int i = 0; i < 15; i++) {
            list.add("菊花残，满腚伤");
        }
        mAdapter.setNewData(list);
    }

    @Override
    protected void initViews() {
        mRefreshLayout = (TwinklingRefreshLayout) findViewById(R.id.tfl_indicator);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_indicator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        IndicatorHeaderView headerView = new IndicatorHeaderView(this);
        headerView.setColor(Color.MAGENTA);
        mRefreshLayout.setHeaderView(headerView);
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
}
