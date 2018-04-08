package com.example.diplomaproject.adapter.holder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.enity.ThinkingBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 鱼握拳 on 2018/4/8.
 */

public class ThingKingViewHolder extends BaseViewHolder<ThinkingBean> {
    private ImageView imageView;
    private TextView time,name,content,share,like,comment;

    public ThingKingViewHolder(ViewGroup parent) {
        super(parent, R.layout.thingking_item);
        imageView = $(R.id.image);
        time=$(R.id.time);
        name=$(R.id.name);
        content=$(R.id.content);
        like=$(R.id.like);
        share=$(R.id.share);
        comment=$(R.id.comment);
    }

    @Override
    public void setData(ThinkingBean data) {
        super.setData(data);
        Glide.with(MyApplication.getContext())
                .load(R.drawable.bg_people)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(imageView);
        time.setText(data.getCreatedAt());
        name.setText(data.getName());
        comment.setText(data.getComment());
        content.setText(data.getContent());
    }
}
