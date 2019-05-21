package com.example.hp.androidlivewallpaper.Model;

public class WallpaperItem {
    public String imageLink;
    public String categoryId;
    public long viewCount;

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

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

    public WallpaperItem() {

    }

    public WallpaperItem(String imageLink, String categoryId) {

        this.imageLink = imageLink;
        this.categoryId = categoryId;
    }
}
