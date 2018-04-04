package com.example.diplomaproject.adapter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.diplomaproject.adapter.holder.ReadViewHolder;
import com.example.diplomaproject.enity.ReadBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public class ReadAdapter extends RecyclerArrayAdapter<ReadBean> {
    public ReadAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReadViewHolder(parent);
    }
}