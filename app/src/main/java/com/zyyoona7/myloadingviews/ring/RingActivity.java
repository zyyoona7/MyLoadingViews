package com.zyyoona7.myloadingviews.ring;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zyyoona7.loading.view.RotateRingLoadingView;
import com.zyyoona7.myloadingviews.BaseActivity;
import com.zyyoona7.myloadingviews.R;

public class RingActivity extends BaseActivity {

    private RotateRingLoadingView mRingLoadingView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ring;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mRingLoadingView = (RotateRingLoadingView) findViewById(R.id.ring_loading);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                mRingLoadingView.startAnim();
//            }
//        }, 2000);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRingLoadingView != null) {
            mRingLoadingView.stopAnim();
        }
    }
}
