package com.misutesu.project.lib_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.lib_base.utils.ActivityUtils;
import com.misutesu.project.lib_base.utils.EventBusUtils;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    protected P mPresenter;

    protected abstract P bindPresenter();

    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtils.add(this);

        //InitPresenter
        mPresenter = bindPresenter();
        if (mPresenter != null) {
            mPresenter.bindLifecycle(this);
        }

        if (useEventBus()) {
            EventBusUtils.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.remove(this);
        if (useEventBus()) {
            EventBusUtils.unBind(this);
        }
    }
}
