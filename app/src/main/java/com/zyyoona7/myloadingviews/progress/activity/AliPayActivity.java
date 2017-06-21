package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.AliPayHeaderView;

public class AliPayActivity extends BaseProgressActivity {


    @Override
    protected IHeaderView setHeaderView() {
        AliPayHeaderView headerView=new AliPayHeaderView(this);
        headerView.setColor(Color.parseColor("#C5C1AA"), Color.parseColor("#EE4000"));
        return headerView;
    }
}
