package com.example.hp.androidlivewallpaper.Database.LocalDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hp.androidlivewallpaper.Database.Recents;

import static com.example.hp.androidlivewallpaper.Database.LocalDatabase.LocalDatabase.DATABASE_VERSION;


@Database(entities = Recents.class, version = DATABASE_VERSION)
public abstract class LocalDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LiveWallpaper";

    public abstract RecentDAO recentDAO();

    private static LocalDatabase instance;

    public static LocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;

    }
}
//9,13