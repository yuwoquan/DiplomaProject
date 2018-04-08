package com.example.diplomaproject.mvp.thingKind;

import android.os.Handler;
import android.util.Log;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.enity.ThinkingBean;
import com.example.diplomaproject.mvp.base.AbstractMvpPersenter;
import com.example.diplomaproject.mvp.base.IBaseListCallBack;


import java.util.List;

/**
 * Created by 鱼握拳 on 2018/4/8.
 */

public class ThinkPesenter extends AbstractMvpPersenter<ThinkView> {
    private ThinkModel readModel;
    public ThinkPesenter() {
        readModel = new ThinkModel(MyApplication.getContext());
    }
    public void clickPost(final int page, final int actionType) {
        if (getmMvpView() != null) {
            getmMvpView().requestLoading();
        }
        //模拟网络延迟，可以显示出加载中
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readModel.RequestPost(page,actionType,new IBaseListCallBack<ThinkingBean>() {
                    @Override
                    public void requestError(Throwable throwable) {
                        if (getmMvpView() != null) {
                            getmMvpView().resultFailure(Log.getStackTraceString(throwable));
                        }
                    }

                    @Override
                    public void requestSuccess(List<ThinkingBean> callBack) {
                        if (getmMvpView() != null) {
                            getmMvpView().resultSuccess(callBack);
                        }
                    }
                });

            }
        }, 1);
    }

    public void interruptHttp() {
        readModel.interruptHttp();
    }
}