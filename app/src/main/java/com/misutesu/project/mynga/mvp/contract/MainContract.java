package com.misutesu.project.mynga.mvp.contract;

import com.misutesu.project.lib_base.mvp.IModel;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.mynga.entity.AllPlate;

import io.reactivex.Observable;

public interface MainContract {
    interface Model extends IModel {

        Observable<AllPlate> getAllPlat();
    }

    interface View extends IView {

        void getAllPlatSuccess(AllPlate allPlate);

        void getAllPlatError();

        void getAllPlatEnd();
    }

    interface Presenter extends IPresenter {

        void getAllPlat();
    }
}
