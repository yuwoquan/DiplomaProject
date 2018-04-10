package com.example.diplomaproject.ui.fragment;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.adapter.adapter.ReadAdapter;
import com.example.diplomaproject.adapter.adapter.ThingKingAdapter;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.enity.ThinkingBean;
import com.example.diplomaproject.mvp.base.AbstractMvpFragment;
import com.example.diplomaproject.mvp.thingKind.ThinkPesenter;
import com.example.diplomaproject.mvp.thingKind.ThinkView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ThingKingFragment extends AbstractMvpFragment<ThinkView, ThinkPesenter> implements ThinkView {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.easyrecycleview)
    EasyRecyclerView recycleview;
    private ThingKingAdapter thingKingAdapter;
    private List<ThinkingBean> thinkingBeans = new ArrayList<>();
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_thing_king, null);
        ButterKnife.bind(this, layout);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycleview.setLayoutManager(staggeredGridLayoutManager);
        thingKingAdapter = new ThingKingAdapter(MyApplication.getContext());
        recycleview.setAdapter(thingKingAdapter);
        recycleview.setRefreshing(true);
        getPresenter().clickPost(0, STATE_REFRESH);
        initTopBar();
        return layout;
    }
    private void initTopBar() {
        topbar.setTitle("ThingKing");
    }
    @Override
    public void requestLoading() {

    }
    @Override
    protected boolean canDragBack() {
        return false;
    }
    @Override
    public void resultSuccess(List<ThinkingBean> result) {
            thingKingAdapter.addAll(result);
    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public ThinkPesenter createPresenter() {
        return new ThinkPesenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
