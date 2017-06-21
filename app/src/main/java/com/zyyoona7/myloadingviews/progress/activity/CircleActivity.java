package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.CircleHeaderView;

public class CircleActivity extends BaseProgressActivity {

    @Override
    protected IHeaderView setHeaderView() {
        CircleHeaderView headerView=new CircleHeaderView(this);
        headerView.setColor(Color.parseColor("#BABABA"));
        return headerView;
    }
}
