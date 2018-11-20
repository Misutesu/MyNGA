package com.misutesu.project.mynga.mvp.presenter;

import com.misutesu.project.lib_base.base.BasePresenter;
import com.misutesu.project.mynga.config.ServerConfig;
import com.misutesu.project.mynga.mvp.contract.InitContract;
import com.misutesu.project.mynga.mvp.model.InitModelImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

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
        addDisposable(mModel.getServerInfo()
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
