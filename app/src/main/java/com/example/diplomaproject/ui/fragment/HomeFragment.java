package com.example.diplomaproject.ui.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.adapter.adapter.ReadAdapter;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.mvp.base.AbstractMvpFragment;
import com.example.diplomaproject.mvp.read.presenter.ReadPresener;
import com.example.diplomaproject.mvp.read.view.ReadView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;


public class HomeFragment extends AbstractMvpFragment<ReadView, ReadPresener> implements ReadView {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.easyrecycleview)
    EasyRecyclerView recycleview;
    private ReadAdapter readAdapter;
    private List<ReadBean> readBeans = new ArrayList<>();
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycleview.setLayoutManager(staggeredGridLayoutManager);
        readAdapter = new ReadAdapter(MyApplication.getContext());
        recycleview.setAdapter(readAdapter);
        recycleview.setRefreshing(true);
        getPresenter().clickPost(0,STATE_REFRESH);

        initTopBar();
        return layout;
    }

    private void initTopBar() {
        topbar.setTitle("Read");
        topbar.addLeftImageButton(R.mipmap.menu,R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        topbar.addRightImageButton(R.mipmap.mine, R.id.topbar_right_about_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyApplication.getContext(), "77", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void requestLoading() {

    }

    @Override
    public void resultSuccess(List<ReadBean> result) {
            readAdapter.addAll(result);
    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public ReadPresener createPresenter() {
        return new ReadPresener();
    }

 
}
