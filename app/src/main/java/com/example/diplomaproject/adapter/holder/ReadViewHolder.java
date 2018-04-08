package com.example.diplomaproject.adapter.holder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.enity.ReadBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public class ReadViewHolder extends BaseViewHolder<ReadBean> {
    private ImageView imageView;
    private TextView description;

    public ReadViewHolder(ViewGroup parent) {
        super(parent, R.layout.read_item);
        imageView=$(R.id.iv_head);
        description=$(R.id.des);
    }

    @Override
    public void setData(ReadBean data) {
        super.setData(data);
        description.setText(data.getDetail());
        Glide.with(MyApplication.getContext()).load(data.getJpg().getUrl()).into(imageView);

    }
}
