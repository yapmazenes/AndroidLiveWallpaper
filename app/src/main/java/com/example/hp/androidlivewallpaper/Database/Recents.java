package com.example.hp.androidlivewallpaper.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "recents", primaryKeys = {"imageLink", "categoryId"})
public class Recents {

    @ColumnInfo(name = "imageLink")
    @NonNull
    private String imageLink;

    @ColumnInfo(name = "categoryId")
    @NonNull
    private String categoryId;

    public String getImageLink() {

        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(String savedTime) {
        this.savedTime = savedTime;
    }

    public Recents(String imageLink, String categoryId, String savedTime, String key) {
        this.imageLink = imageLink;
        this.categoryId = categoryId;
        this.savedTime = savedTime;
        this.key = key;
    }

    @ColumnInfo(name = "savedTime")
    private String savedTime;

    @ColumnInfo(name="key")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
