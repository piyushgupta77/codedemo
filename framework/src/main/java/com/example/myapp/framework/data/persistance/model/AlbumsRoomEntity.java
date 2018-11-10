package com.example.myapp.framework.data.persistance.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.squareup.moshi.Json;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

@Entity
public class AlbumsRoomEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "album_id")
    public int albumId;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "title")
    public String title;

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

}
