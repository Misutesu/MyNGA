package com.misutesu.project.mynga.mvp.contract;

import com.misutesu.project.lib_base.mvp.IModel;
import com.misutesu.project.lib_base.mvp.IPresenter;
import com.misutesu.project.lib_base.mvp.IView;
import com.misutesu.project.mynga.entity.Server;

import java.util.List;

import io.reactivex.Observable;

public interface InitContract {

    interface Model extends IModel {

        Observable<List<Server>> getServerInfo();
    }

    interface View extends IView {
        void getServerInfoSuccess();

        void getServerInfoError();
    }

    interface Presenter extends IPresenter {
        void getServerInfo();
    }
}
