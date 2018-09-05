package com.misutesu.project.lib_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.lib_base.utils.ActivityUtils;
import com.misutesu.project.lib_base.utils.BaseUtils;
import com.misutesu.project.lib_base.utils.EventBusUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    private Unbinder unbinder;

    protected P mPresenter;

    protected abstract P bindPresenter();

    protected boolean useEventBus() {
        return false;
    }

    protected abstract int bindXML();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseUtils.hideStatusBar(this);

        //ButterKnife
        int layoutID = bindXML();
        if (layoutID != 0) {
            setContentView(layoutID);
            unbinder = ButterKnife.bind(this);
        }

        //ActivityManager
        ActivityUtils.add(this);

        //InitPresenter
        mPresenter = bindPresenter();

        //EventBus
        if (useEventBus()) {
            EventBusUtils.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
        ActivityUtils.remove(this);
        if (useEventBus()) {
            EventBusUtils.unBind(this);
        }
    }
}
