package com.misutesu.project.mynga.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.misutesu.project.mynga.entity.Plate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface CollectPlateDao {

    @Query("select * from collect_plat")
    Flowable<List<Plate>> getCollectPlate();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertCollectPlat(Plate plate);
}
