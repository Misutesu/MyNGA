package com.misutesu.project.lib_base.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.lib_base.utils.EventBusUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {

    private Unbinder unbinder;

    private View fragmentView;

    protected P mPresenter;

    protected abstract P bindPresenter();

    protected abstract int bindXML();

    protected boolean useEventBus() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fragmentView == null) {
            int layoutID = bindXML();
            if (layoutID != 0) {
                fragmentView = inflater.inflate(bindXML(), container, false);
                unbinder = ButterKnife.bind(this, fragmentView);
            }
        }
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = bindPresenter();

        if (useEventBus()) {
            EventBusUtils.bind(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
        if (useEventBus()) {
            EventBusUtils.unBind(this);
        }
    }
}
