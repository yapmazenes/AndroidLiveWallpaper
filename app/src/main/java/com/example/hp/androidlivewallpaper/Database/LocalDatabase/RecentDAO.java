package com.example.hp.androidlivewallpaper.Database.LocalDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hp.androidlivewallpaper.Database.Recents;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RecentDAO {
    @Query("Select * from recents order by savedTime desc limit 10")
    Flowable<List<Recents>> getAllRecents();

    @Insert
    void insertRecents(Recents... recents);
    @Update
    void updateRecents(Recents... recents);
    @Delete
    void deleteRecents(Recents... recents);
    @Query("Delete from recents")
    void deleteAllRecents();
}
