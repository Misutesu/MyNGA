package com.misutesu.project.mynga.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.misutesu.project.mynga.config.DatabaseConfig;

public class RoomManager {

    private static NGADatabase ngaDatabase;

    public static void init(Context context) {
        ngaDatabase = Room.databaseBuilder(context, NGADatabase.class, DatabaseConfig.DATABASE_NAME).build();
    }

    public static NGADatabase getNGADatabase() {
        return ngaDatabase;
    }
}
