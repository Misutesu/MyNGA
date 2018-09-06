package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.entity.AllPlate;
import com.misutesu.project.mynga.mvp.contract.MainContract;
import com.misutesu.project.mynga.mvp.presenter.MainPresenterImpl;
import com.misutesu.project.mynga.router.DiscussRouter;
import com.misutesu.project.mynga.router.SystemRouter;

import timber.log.Timber;

@Route(group = DiscussRouter.group, path = DiscussRouter.main)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
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

        mPresenter.getAllPlat();
    }

    @Override
    public void getAllPlatSuccess(AllPlate allPlate) {

    }

    @Override
    public void getAllPlatError() {

    }

    @Override
    public void getAllPlatEnd() {

    }
}
