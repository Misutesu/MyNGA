package com.misutesu.project.mynga.mvp.model;

import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.mynga.entity.Server;
import com.misutesu.project.mynga.mvp.contract.InitContract;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import rx.Observable;

public class InitModelImpl extends BaseModel implements InitContract.Model {

    @Override
    public Observable<List<Server>> getServerInfo() {
        return new BmobQuery<Server>().findObjectsObservable(Server.class);
    }
}
