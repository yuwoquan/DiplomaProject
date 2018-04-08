package com.example.diplomaproject.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.diplomaproject.MyApplication;
import com.example.diplomaproject.R;
import com.example.diplomaproject.adapter.adapter.ReadAdapter;
import com.example.diplomaproject.enity.ReadBean;
import com.example.diplomaproject.mvp.base.AbstractMvpFragment;
import com.example.diplomaproject.mvp.read.ReadPresener;
import com.example.diplomaproject.mvp.read.ReadView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends AbstractMvpFragment<ReadView, ReadPresener> implements ReadView {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.easyrecycleview)
    EasyRecyclerView recycleview;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    private ReadAdapter readAdapter;
    private List<ReadBean> readBeans = new ArrayList<>();
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;
    private boolean isDrawer = false;

    @Override
    protected View onCreateView() {
        DrawerLayout layout = (DrawerLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycleview.setLayoutManager(staggeredGridLayoutManager);
        readAdapter = new ReadAdapter(MyApplication.getContext());
        recycleview.setAdapter(readAdapter);
        recycleview.setRefreshing(true);
        getPresenter().clickPost(0, STATE_REFRESH);
        init();
        initTopBar();

        return layout;
    }

    private void init() {
        framelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isDrawer) {
                    return navigationView.dispatchTouchEvent(event);
                } else {
                    return false;
                }
            }
        });


        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer=true;
                WindowManager manager= (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                Display display=manager.getDefaultDisplay();
                framelayout.layout(navigationView.getRight(),0,navigationView.getRight()+display.getWidth(),display.getHeight());
            }
            public void onDrawerOpened(View drawerView) {}
            public void onDrawerClosed(View drawerView) {}
            public void onDrawerStateChanged(int newState){}
        });

//切换fragment
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.book:
                       startFragment(new ThingKingFragment());
                        break;

                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    @Override
    protected boolean canDragBack() {
        return false;
    }

    private void initTopBar() {
        topbar.setTitle("Read");
        topbar.addLeftImageButton(R.mipmap.menu, R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
