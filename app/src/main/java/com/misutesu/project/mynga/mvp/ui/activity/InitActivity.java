package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.entity.Server;
import com.misutesu.project.mynga.mvp.contract.InitContract;
import com.misutesu.project.mynga.mvp.presenter.InitPresenterImpl;
import com.misutesu.project.mynga.router.SystemRouter;

import java.util.List;

@Route(group = SystemRouter.group, path = SystemRouter.init)
public class InitActivity extends BaseActivity<InitContract.Presenter> implements InitContract.View {

    @Override
    protected InitContract.Presenter bindPresenter() {
        return new InitPresenterImpl(this);
    }

    @Override
    protected int bindXML() {
        return R.layout.activity_init;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseUtils.hideNavigationBar(this);

        mPresenter.getServerInfo();
    }

    @Override
    public void getServerInfoSuccess() {

    }

    @Override
    public void getServerInfoError() {

    }
}
