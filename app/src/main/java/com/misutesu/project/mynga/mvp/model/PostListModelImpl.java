package com.misutesu.project.mynga.mvp.model;

import com.google.gson.Gson;
import com.misutesu.project.lib_base.base.BaseModel;
import com.misutesu.project.lib_base.http.RetrofitManager;
import com.misutesu.project.mynga.api.PlateApi;
import com.misutesu.project.mynga.data.PostList;
import com.misutesu.project.mynga.database.RoomManager;
import com.misutesu.project.mynga.entity.Plate;
import com.misutesu.project.mynga.mvp.contract.PostListContract;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PostListModelImpl extends BaseModel implements PostListContract.Model {

    @Override
    public Single<Plate> getCollectPlate(int id) {
        return RoomManager.getNGADatabase().getCollectPlateDao().getPlate(id);
    }

    @Override
    public void insertPlate(Plate plate) {
        RoomManager.getNGADatabase().getCollectPlateDao().insertPlat(plate);
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

    public static <T> T get(Class<T> cT) {
        try {
            Class<T> c = (Class<T>) Class.forName(cT.getName());
            return c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
