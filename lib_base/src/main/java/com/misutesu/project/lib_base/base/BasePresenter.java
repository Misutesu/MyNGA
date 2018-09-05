package com.misutesu.project.lib_base.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.misutesu.project.lib_base.mvp.IModel;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter, LifecycleObserver {

    private CompositeSubscription mSubscription;
    private CompositeDisposable mDisposable;

    protected V mRootView;

    public BasePresenter(V view) {
        mRootView = view;
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
        }
    }

    public void addSubscription(Subscription subScription) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subScription);
    }

    public void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }
}
