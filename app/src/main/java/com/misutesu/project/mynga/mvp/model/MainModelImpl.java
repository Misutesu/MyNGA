package com.misutesu.project.mynga.mvp.model;

import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.lib_base.http.RetrofitManager;
import com.misutesu.project.mynga.api.PlateApi;
import com.misutesu.project.mynga.entity.AllPlate;
import com.misutesu.project.mynga.mvp.contract.MainContract;

import io.reactivex.Observable;

public class MainModelImpl extends BaseModel implements MainContract.Model {

    @Override
    public Observable<AllPlate> getAllPlat() {
        return RetrofitManager.getInstance().create(PlateApi.class).getAllPlate();
    }
}
