package com.misutesu.project.lib_base.mvp;

import io.reactivex.disposables.Disposable;

public interface IPresenter {

    void bindLifecycle(IView view);

    void addDisposable(Disposable disposable);
}
