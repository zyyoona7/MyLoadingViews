package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.RingHeaderView;

public class RingActivity extends BaseProgressActivity {

    @Override
    protected IHeaderView setHeaderView() {
        RingHeaderView headerView = new RingHeaderView(this);
        headerView.setColor(Color.BLUE);
        return headerView;
    }
}
