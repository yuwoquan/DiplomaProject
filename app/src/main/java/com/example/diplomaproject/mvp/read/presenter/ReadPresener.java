package com.example.diplomaproject.mvp.read.presenter;

import android.os.Handler;
import android.util.Log;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.mvp.base.AbstractMvpPersenter;
import com.example.diplomaproject.mvp.base.IBaseListCallBack;
import com.example.diplomaproject.mvp.read.model.ReadModel;
import com.example.diplomaproject.mvp.read.view.ReadView;

import java.util.List;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public class ReadPresener extends AbstractMvpPersenter<ReadView> {
    private ReadModel readModel;
    public ReadPresener() {
        readModel = new ReadModel(MyApplication.getContext());
    }
    public void clickPost(final int page, final int actionType) {
        if (getmMvpView() != null) {
            getmMvpView().requestLoading();
        }
        //模拟网络延迟，可以显示出加载中
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readModel.RequestPost(page,actionType,new IBaseListCallBack<ReadBean>() {
                    @Override
                    public void requestError(Throwable throwable) {
                        if (getmMvpView() != null) {
                            getmMvpView().resultFailure(Log.getStackTraceString(throwable));
                        }
                    }

                    @Override
                    public void requestSuccess(List<ReadBean> callBack) {
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
