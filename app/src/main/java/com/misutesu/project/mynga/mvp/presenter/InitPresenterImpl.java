package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.entity.Server;
import com.misutesu.project.mynga.mvp.contract.InitContract;
import com.misutesu.project.mynga.mvp.model.InitModelImpl;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class InitPresenterImpl extends BasePresenter<InitContract.Model, InitContract.View> implements InitContract.Presenter {

    public InitPresenterImpl(InitContract.View view) {
        super(view);
    }

    @Override
    protected InitContract.Model bindModel() {
        return new InitModelImpl();
    }

    @Override
    public void getServerInfo() {
        addSubscription(mModel.getServerInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(servers -> {
                            ServerConfig.setServers(servers);
                            RetrofitUrlManager.getInstance().setGlobalDomain(ServerConfig.getUrl(ServerConfig.SERVER_API));
                            mRootView.getServerInfoSuccess();
                        }
                        , throwable -> mRootView.getServerInfoError()));
    }
}
