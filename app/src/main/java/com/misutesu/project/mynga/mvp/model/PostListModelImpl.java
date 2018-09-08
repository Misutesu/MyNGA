package com.misutesu.project.mynga.mvp.model;

import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.lib_base.http.RetrofitManager;
import com.misutesu.project.mynga.api.PlateApi;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.database.RoomManager;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PostListContract;

import java.util.UUID;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class PostListModelImpl extends BaseModel implements PostListContract.Model {

    @Override
    public Single<Plate> getCollectPlate(int id) {
        return RoomManager.getNGADatabase().getCollectPlateDao().getPlate(id);
    }

    @Override
    public void insertPlate(Plate plate) {
        RoomManager.getNGADatabase().getCollectPlateDao().insertCollectPlat(plate);
    }

    @Override
    public void deletePlate(Plate plate) {
        RoomManager.getNGADatabase().getCollectPlateDao().deletePlate(plate);
    }

    @Override
    public Observable<PostList> getPostList(int plateId, int page) {
        return RetrofitManager.getInstance().create(PlateApi.class)
                .getPostList(plateId, page, System.currentTimeMillis(), UUID.randomUUID().toString());
    }
}
