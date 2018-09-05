package com.misutesu.project.mynga.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.misutesu.project.lib_base.base.BaseActivity;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.mynga.R;
import com.misutesu.project.mynga.entity.Server;
import com.misutesu.project.mynga.mvp.contract.InitContract;
import com.misutesu.project.mynga.mvp.presenter.InitPresenter;

import java.util.List;

import timber.log.Timber;

public class InitActivity extends BaseActivity<InitPresenter> implements InitContract.View {

    @Override
    protected InitPresenter bindPresenter() {
        return new InitPresenter(this);
    }

    @Override
    protected int bindXML() {
        return R.layout.activity_init;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseUtils.hideNavigationBar(this);
    }

    @Override
    public void getServerInfoSuccess(List<Server> servers) {
        Timber.d("TAG:%s", servers.toString());
    }

    @Override
    public void getServerInfoError() {

    }
}
