package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.entity.Server;
import com.misutesu.project.mynga.mvp.contract.InitContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InitPresenter extends BasePresenter<InitContract.Model, InitContract.View> implements InitContract.Presenter {

    public InitPresenter(InitContract.View view) {
        super(view);
    }

    @Override
    public void getServerInfo() {
        addSubscription(new BmobQuery<Server>().findObjectsObservable(Server.class)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Server>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mRootView.getServerInfoError();
                    }

                    @Override
                    public void onNext(List<Server> servers) {
                        mRootView.getServerInfoSuccess(servers);
                    }
                }));
    }
}
