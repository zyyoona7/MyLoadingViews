package com.zyyoona7.myloadingviews.progress.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyyoona7.myloadingviews.R;

/**
 * Created by zyyoona7 on 2017/6/9.
 */

public class RecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RecyclerAdapter() {
        super(R.layout.rv_item_base, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_indicator, item);
    }
}
