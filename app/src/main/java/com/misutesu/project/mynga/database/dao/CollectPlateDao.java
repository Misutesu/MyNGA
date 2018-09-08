package com.misutesu.project.mynga.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.misutesu.project.mynga.entity.Plate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CollectPlateDao {

    @Query("select * from collect_plat")
    Flowable<List<Plate>> getCollectPlate();

    @Query("select * from collect_plat where id = :id")
    Single<Plate> getPlate(int id);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertCollectPlat(Plate plate);

    @Update
    void updatePlate(Plate plate);

    @Delete
    void deletePlate(Plate plate);
}
