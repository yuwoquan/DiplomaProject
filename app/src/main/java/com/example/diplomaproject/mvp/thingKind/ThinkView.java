package com.example.diplomaproject.mvp.thingKind;

import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.enity.ThinkingBean;
import com.example.diplomaproject.mvp.base.IMvpBaseView;

import java.util.List;

/**
 * Created by 鱼握拳 on 2018/4/8.
 */

public interface ThinkView extends IMvpBaseView {
    void requestLoading();
    void resultSuccess(List<ThinkingBean> result);
    void resultFailure(String result);
}