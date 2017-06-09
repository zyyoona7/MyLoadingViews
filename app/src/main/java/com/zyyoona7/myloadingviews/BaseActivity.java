package com.zyyoona7.myloadingviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zyyoona7 on 2017/6/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        }

        initVariables();

        initViews();

        initEvents();
    }

    protected abstract int getLayoutID();

    protected abstract void initVariables();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected void goTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
