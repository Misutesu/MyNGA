package com.misutesu.project.mynga.mvp.contract;

import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.lib_base.mvp.IModel;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.mynga.entity.Plate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface PlateContract {
    interface Model extends IModel {

        Flowable<List<Plate>> getCollectPlats();

        void updatePlates(List<Plate> plates);
    }

    interface View extends IView {

        void getCollectPlatsSuccess(List<Plate> plates);

        void getCollectPlatsError();
    }

    interface Presenter extends IPresenter {

        void getCollectPlats();

        void exchangePlate(Plate fromPlate, Plate toPlate);
    }
}
