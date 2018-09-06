package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.mvp.contract.MainContract;
import com.misutesu.project.mynga.mvp.model.MainModelImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl extends BasePresenter<MainContract.Model, MainContract.View> implements MainContract.Presenter {

    public MainPresenterImpl(MainContract.View view) {
        super(view);
    }

    @Override
    protected MainContract.Model bindModel() {
        return new MainModelImpl();
    }

    @Override
    public void getAllPlat() {
        addDisposable(mModel.getAllPlat()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> mRootView.getAllPlatEnd())
                .doOnError(throwable -> mRootView.getAllPlatEnd())
                .subscribe(allPlate -> mRootView.getAllPlatSuccess(allPlate)));
    }
}
