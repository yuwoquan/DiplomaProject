package com.example.diplomaproject.adapter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.diplomaproject.adapter.holder.ReadViewHolder;
import com.example.diplomaproject.adapter.holder.ThingKingViewHolder;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.enity.ThinkingBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鱼握拳 on 2018/4/8.
 */

public class ThingKingAdapter extends RecyclerArrayAdapter<ThinkingBean> {
    public ThingKingAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThingKingViewHolder(parent);
    }
}
