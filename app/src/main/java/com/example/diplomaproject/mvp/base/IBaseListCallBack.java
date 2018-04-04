package com.example.diplomaproject.mvp.base;

import java.util.List;

/**
 * Created by 鱼握拳 on 2018/3/6.
 */

public interface IBaseListCallBack<T> {
    /**
     * @descriptoin	请求异常
     */
    void requestError(Throwable throwable);

    /**
     * @descriptoin	请求成功
     */
    void requestSuccess(List<T> callBack);
}
