package com.example.diplomaproject.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.diplomaproject.R;
import com.example.diplomaproject.ui.base.BaseFragment;
import com.example.diplomaproject.utils.util.NoScrollViewPager;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TabFragment extends BaseFragment {

    @BindView(R.id.pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab, null);
        ButterKnife.bind(this, layout);
        initPagers();
        initTabs();
        return layout;
    }
    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
    }

    private void initPagers() {
        QMUIPagerAdapter pagerAdapter = new QMUIPagerAdapter() {
            private FragmentTransaction mCurrentTransaction;
            private android.support.v4.app.Fragment mCurrentPrimaryItem = null;

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == ((android.support.v4.app.Fragment) object).getView();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Button";
                    case 1:
                        return "CollapsingTopBar";
                    case 2:
                    default:
                        return "About";
                }
            }

            @Override
            protected Object hydrate(ViewGroup container, int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new MineFragment();
                    case 2:
                    default:
                        return new ThingKingFragment();
                }
            }

            @SuppressLint("CommitTransaction")
            @Override
            protected void populate(ViewGroup container, Object item, int position) {
                String name = makeFragmentName(container.getId(), position);
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getChildFragmentManager()
                            .beginTransaction();
                }
                android.support.v4.app.Fragment fragment = getChildFragmentManager().findFragmentByTag(name);
                if (fragment != null) {
                    mCurrentTransaction.attach(fragment);
                } else {
                    fragment = (android.support.v4.app.Fragment) item;
                    mCurrentTransaction.add(container.getId(), fragment, name);
                }
                if (fragment != mCurrentPrimaryItem) {
                    fragment.setMenuVisibility(false);
                    fragment.setUserVisibleHint(false);
                }
            }

            @SuppressLint("CommitTransaction")
            @Override
            protected void destroy(ViewGroup container, int position, Object object) {
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getChildFragmentManager()
                            .beginTransaction();
                }
                mCurrentTransaction.detach((android.support.v4.app.Fragment) object);
            }

            @Override
            public void startUpdate(ViewGroup container) {
                if (container.getId() == View.NO_ID) {
                    throw new IllegalStateException("ViewPager with adapter " + this
                            + " requires a view id");
                }
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                if (mCurrentTransaction != null) {
                    mCurrentTransaction.commitNowAllowingStateLoss();
                    mCurrentTransaction = null;
                }
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) object;
                if (fragment != mCurrentPrimaryItem) {
                    if (mCurrentPrimaryItem != null) {
                        mCurrentPrimaryItem.setMenuVisibility(false);
                        mCurrentPrimaryItem.setUserVisibleHint(false);
                    }
                    if (fragment != null) {
                        fragment.setMenuVisibility(true);
                        fragment.setUserVisibleHint(true);
                    }
                    mCurrentPrimaryItem = fragment;
                }
            }

            private String makeFragmentName(int viewId, long id) {
                return "QDFitSystemWindowViewPagerFragment:" + viewId + ":" + id;
            }
        };
        mViewPager.setAdapter(pagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager);
    }

}
