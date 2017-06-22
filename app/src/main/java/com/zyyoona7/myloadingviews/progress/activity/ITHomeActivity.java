package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.ITHomeHeaderView;

public class ITHomeActivity extends BaseProgressActivity {

    @Override
    protected IHeaderView setHeaderView() {
        ITHomeHeaderView headerView=new ITHomeHeaderView(this);
        headerView.setColor(Color.parseColor("#EE0000"));
        return headerView;
    }
}
