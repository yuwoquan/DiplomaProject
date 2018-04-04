package com.example.diplomaproject.mvp.read.view;

import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.mvp.base.IMvpBaseView;

import java.util.List;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public interface ReadView extends IMvpBaseView {
    void requestLoading();
    void resultSuccess(List<ReadBean> result);
    void resultFailure(String result);
}
