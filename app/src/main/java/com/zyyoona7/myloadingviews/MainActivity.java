package com.zyyoona7.myloadingviews;

import android.view.View;
import android.widget.Button;

import com.zyyoona7.myloadingviews.indicator.IndicatorActivity;

public class MainActivity extends BaseActivity {
    private Button mJuHuaBtn;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mJuHuaBtn = (Button) findViewById(R.id.btn_juhua);

    }

    @Override
    protected void initEvents() {
        mJuHuaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(IndicatorActivity.class);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
