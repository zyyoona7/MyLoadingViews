package com.zyyoona7.myloadingviews;

import android.view.View;
import android.widget.Button;

import com.zyyoona7.myloadingviews.progress.activity.ProgressLoadingActivity;

public class MainActivity extends BaseActivity {
    private Button mRefreshLoadingBtn;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mRefreshLoadingBtn = (Button) findViewById(R.id.btn_refresh);
    }

    @Override
    protected void initEvents() {
        mRefreshLoadingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(ProgressLoadingActivity.class);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
