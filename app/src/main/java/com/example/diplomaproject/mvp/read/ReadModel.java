package com.example.diplomaproject.mvp.read;

import android.content.Context;
import android.util.Log;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.mvp.base.IBaseListCallBack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;
import retrofit2.Call;

/**
 * Created by 鱼握拳 on 2018/4/5.
 */

public class ReadModel {
    private Call<ReadBean> postCall;
    private List<ReadBean> posts =new ArrayList<>();
    private ReadBean post;
    private Context context = null;
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;
    String lastTime = null;
    public ReadModel(Context mcontext) {
        super();
        context = mcontext;
    }
    public void  RequestPost(int page,final int actionType,final IBaseListCallBack<ReadBean> iBaseRequestCallBack){
        BmobQuery<ReadBean> query = new BmobQuery<>();
        query.include("author");
        query.order("-createdAt");

        if (actionType == STATE_MORE) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
                Log.i("0414", date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            query.setSkip(limit*curPage-10);
        } else {
            page = 0;
            query.setSkip(page);
        }
        query.setLimit(limit);
        query.findObjects(MyApplication.getContext(), new FindListener<ReadBean>() {
            @Override
            public void onSuccess(List<ReadBean> list) {
                if (list.size()>0) {
                    if (actionType == STATE_REFRESH) {
                        curPage = 0;
                        posts.clear();
                        lastTime = list.get(list.size() - 1).getCreatedAt();
                    }
                    for (ReadBean td : list) {
                        posts.add(td);
                    }
                    curPage++;
                } else if (actionType == STATE_MORE) {

                } else if (actionType == STATE_REFRESH) {

                }
                iBaseRequestCallBack.requestSuccess(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    public void interruptHttp(){
        if(postCall!= null && !postCall.isCanceled()){
            postCall.cancel();
        }
    }
}