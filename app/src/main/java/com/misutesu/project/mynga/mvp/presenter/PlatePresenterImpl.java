package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.mvp.contract.PlateContract;
import com.misutesu.project.mynga.mvp.model.PlateModelImpl;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PlatePresenterImpl extends BasePresenter<PlateContract.Model, PlateContract.View> implements PlateContract.Presenter {

    public PlatePresenterImpl(PlateContract.View view) {
        super(view);
    }

    @Override
    protected PlateContract.Model bindModel() {
        return new PlateModelImpl();
    }

    @Override
    public void getCollectPlats() {
        addDisposable(mModel.getCollectPlats()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mRootView.getCollectPlatsError())
                .subscribe(plates -> mRootView.getCollectPlatsSuccess(plates)));
    }
}
