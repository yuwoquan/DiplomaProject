package com.example.diplomaproject.mvp.base;

import android.os.Bundle;

import com.example.diplomaproject.ui.base.BaseFragment;


/**
 * Created by 鱼握拳 on 2018/3/13.
 */

public abstract class AbstractMvpFragment<V extends IMvpBaseView, P extends AbstractMvpPersenter<V>> extends BaseFragment implements IMvpBaseView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter =createPresenter();
        try {
            if (presenter == null) {
                presenter = createPresenter();
            }

            if (presenter == null) {
                throw new NullPointerException("presenter 不能为空!");
            }
            //绑定view
            presenter.attachMvpView((V) this);
        } catch (Exception e) {
            new ClassCastException(this.toString() + "实现IPresenterView或者IPresenterView子类接口");
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachMvpView();
        }
        super.onDestroy();
    }
    public P getPresenter() {
        return presenter;
    }
    //实例化Presenter
    public abstract P createPresenter();
}
