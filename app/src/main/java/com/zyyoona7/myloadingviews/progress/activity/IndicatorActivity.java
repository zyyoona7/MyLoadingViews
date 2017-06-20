package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.IndicatorHeaderView;

public class IndicatorActivity extends BaseProgressActivity {


    @Override
    protected IHeaderView setHeaderView() {
        IndicatorHeaderView headerView = new IndicatorHeaderView(this);
        headerView.setColor(Color.MAGENTA);
        return headerView;
    }
}
