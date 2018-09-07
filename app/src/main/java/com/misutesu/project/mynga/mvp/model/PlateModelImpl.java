package com.misutesu.project.mynga.mvp.model;

import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.mynga.database.RoomManager;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PlateContract;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class PlateModelImpl extends BaseModel implements PlateContract.Model {

    @Override
    public Flowable<List<Plate>> getCollectPlats() {
        return RoomManager.getNGADatabase().getCollectPlateDao().getCollectPlate();
    }
}
