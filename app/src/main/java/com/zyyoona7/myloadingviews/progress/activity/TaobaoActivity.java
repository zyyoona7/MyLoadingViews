package com.zyyoona7.myloadingviews.progress.activity;

import android.graphics.Color;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.zyyoona7.myloadingviews.progress.BaseProgressActivity;
import com.zyyoona7.myloadingviews.progress.header.TaobaoHeaderView;

public class TaobaoActivity extends BaseProgressActivity {

    @Override
    protected IHeaderView setHeaderView() {
        TaobaoHeaderView headerView=new TaobaoHeaderView(this);
        headerView.setColor(Color.parseColor("#B2DFEE"));
        return headerView;
    }
}
