package com.ankur.photostream.presentation.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ankur.photostream.R;
import com.ankur.photostream.presentation.adapter.PagerAdapter;
import com.ankur.photostream.presentation.view.PagerSlidingTabStrip;
import com.ankur.photostream.utils.LogUtils;

public class PagerFragment extends BaseFragment {
    private ViewPager            mViewPager;

    private PagerAdapter         mPagerAdapter;

    private String               mFragmentTagSuffix = "";

    private static final String  FRAGMENT_TAG       = PagerFragment.class.getName();

    private String               LOG_TAG            = "PAGER_FRAGMENT";

    private PagerSlidingTabStrip mTabsStrip;

    public void setFragmentTagSuffix(String fragmentTagSuffix) {
        mFragmentTagSuffix = fragmentTagSuffix;
    }

    @Override
    public String getFragmentTag() {
        return FRAGMENT_TAG + mFragmentTagSuffix;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewBundle(getArguments());
    }

    @Override
    public void onNewBundle(Bundle bundle) {
        super.onNewBundle(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_dummy_container, container, false);
        findViews(v);
        bindViews();
        return v;
    }

    private void findViews(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_search_pager);
        mTabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void bindViews() {
        mPagerAdapter = new PagerAdapter(this.getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabsStrip.setViewPager(mViewPager);
        mTabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtils.debugLog(LOG_TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.debugLog(LOG_TAG, "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtils.debugLog(LOG_TAG, "onPageScrollStateChanged:");
            }
        });
    }

    public static PagerFragment newInstance() {
        return newInstance(null);
    }

    public static PagerFragment newInstance(Bundle bundle) {
        PagerFragment fragment = new PagerFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

}
