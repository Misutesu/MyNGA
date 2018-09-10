package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PlateContract;
import com.misutesu.project.mynga.mvp.model.PlateModelImpl;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mRootView.getCollectPlatsError())
                .subscribe(plates -> mRootView.getCollectPlatsSuccess(plates)
                        , throwable -> mRootView.getCollectPlatsError()));
    }

    @Override
    public void exchangePlate(Plate fromPlate, Plate toPlate) {
        addDisposable(Completable.fromAction(() -> {
            int fromCid = fromPlate.getCid();
            int toCid = toPlate.getCid();

            fromPlate.setCid(toCid);
            toPlate.setCid(fromCid);

            List<Plate> list = new ArrayList<>();
            list.add(fromPlate);
            list.add(toPlate);

            mModel.updatePlates(list);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                }, Throwable::printStackTrace));
    }
}
