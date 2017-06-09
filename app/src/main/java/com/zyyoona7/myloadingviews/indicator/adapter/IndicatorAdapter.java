package com.zyyoona7.myloadingviews.indicator.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyyoona7.myloadingviews.R;

/**
 * Created by zyyoona7 on 2017/6/9.
 */

public class IndicatorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public IndicatorAdapter() {
        super(R.layout.rv_item_indicator, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_indicator, item);
    }
}
