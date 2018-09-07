package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.lib_base.utils.UiUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.data.AllPlate;
import com.misutesu.project.mynga.mvp.contract.MainContract;
import com.misutesu.project.mynga.mvp.presenter.MainPresenterImpl;
import com.misutesu.project.mynga.mvp.ui.fragment.PlatFragment;
import com.misutesu.project.mynga.router.DiscussRouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(group = DiscussRouter.group, path = DiscussRouter.main)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected MainContract.Presenter bindPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    protected int bindXML() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        BaseUtils.addStatusBarPadding(appBarLayout);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawerLayout.setFitsSystemWindows(true);
            drawerLayout.setClipToPadding(false);
        }

        mPresenter.getAllPlat();
    }

    @Override
    public void getAllPlatSuccess(AllPlate allPlate) {
        mTitles.add(getString(R.string.my_collection));
        mFragments.add(PlatFragment.getInstance(null));
        for (AllPlate.ResultBean bean : allPlate.getResult()) {
            mTitles.add(bean.getName());
            mFragments.add(PlatFragment.getInstance(bean));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void getAllPlatError() {

    }

    @Override
    public void getAllPlatEnd() {

    }
}
