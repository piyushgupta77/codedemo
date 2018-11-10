package com.example.myapp.framework.data.persistance.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

@Dao
public interface AlbumsDao {
    @Query("SELECT * FROM AlbumsRoomEntity")
    List<AlbumsRoomEntity> getAll();

    @Query("SELECT COUNT(*) FROM AlbumsRoomEntity")
    int getCount();

    @Insert
    void insertAll(List<AlbumsRoomEntity> users);
}
