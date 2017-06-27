package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.zyyoona7.myloadingviews.BaseActivity;
import com.zyyoona7.myloadingviews.R;
import com.zyyoona7.myloadingviews.progress.adapter.RecyclerAdapter;
import com.zyyoona7.myloadingviews.progress.header.AliPayHeaderView;
import com.zyyoona7.myloadingviews.progress.header.CircleHeaderView;
import com.zyyoona7.myloadingviews.progress.header.FriendCircleHeaderView;
import com.zyyoona7.myloadingviews.progress.header.ITHomeHeaderView;
import com.zyyoona7.myloadingviews.progress.header.IndicatorHeaderView;
import com.zyyoona7.myloadingviews.progress.header.RingHeaderView;
import com.zyyoona7.myloadingviews.progress.header.TaobaoHeaderView;
import com.zyyoona7.myloadingviews.progress.header.UCHeaderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/6/20.
 */

public class ProgressLoadingActivity extends BaseActivity {
    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<IHeaderView> mHeaderList;
    private List<String> mDataList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_progress;
    }

    @Override
    protected void initVariables() {
        mAdapter = new RecyclerAdapter();
        mHeaderList = new ArrayList<>();
        mDataList = new ArrayList<>();
        AliPayHeaderView aliPayHeaderView = new AliPayHeaderView(this);
        aliPayHeaderView.setColor(Color.parseColor("#C5C1AA"), Color.parseColor("#EE4000"));
        mHeaderList.add(aliPayHeaderView);
        mDataList.add("支付宝下拉刷新Loading");

        CircleHeaderView headerView = new CircleHeaderView(this);
        headerView.setColor(Color.parseColor("#BABABA"));
        mHeaderList.add(headerView);
        mDataList.add("iOS版小密圈下拉刷新Loading");

        IndicatorHeaderView headerView1 = new IndicatorHeaderView(this);
        headerView1.setColor(Color.MAGENTA);
        mHeaderList.add(headerView1);
        mDataList.add("iOS邮件下拉刷新Loading");

        ITHomeHeaderView headerView2 = new ITHomeHeaderView(this);
        headerView2.setColor(Color.parseColor("#EE0000"));
        mHeaderList.add(headerView2);
        mDataList.add("iOS版IT之家下拉刷新Loading");

        RingHeaderView headerView3 = new RingHeaderView(this);
        headerView3.setColor(Color.BLUE);
        mHeaderList.add(headerView3);
        mDataList.add("腾讯体育下拉刷新Loading");

        TaobaoHeaderView headerView4 = new TaobaoHeaderView(this);
        headerView4.setColor(Color.parseColor("#B2DFEE"));
        mHeaderList.add(headerView4);
        mDataList.add("淘宝下拉刷新Loading");

        FriendCircleHeaderView headerView5 = new FriendCircleHeaderView(this);
        mHeaderList.add(headerView5);
        mDataList.add("朋友圈下拉刷新Loading");

        UCHeaderView headerView6 = new UCHeaderView(this);
        mHeaderList.add(headerView6);
        mDataList.add("UC浏览器头条下拉刷新Loading");
    }

    @Override
    protected void initViews() {
        mRefreshLayout = (TwinklingRefreshLayout) findViewById(R.id.tfl_indicator);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_indicator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter.setNewData(mDataList);
        mRefreshLayout.setHeaderView(mHeaderList.get(0));
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

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position < mHeaderList.size()) {
                    mRefreshLayout.setHeaderView(mHeaderList.get(position));
                    mRefreshLayout.startRefresh();
                }
            }
        });
    }

}
