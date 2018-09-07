package com.misutesu.project.mynga.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.misutesu.project.mynga.database.dao.CollectPlateDao;
import com.misutesu.project.mynga.entity.Plate;

@Database(entities = {Plate.class}, version = 1)
public abstract class NGADatabase extends RoomDatabase {

    public abstract CollectPlateDao getCollectPlateDao();
}
