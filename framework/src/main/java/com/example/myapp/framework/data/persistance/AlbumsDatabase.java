package com.example.myapp.framework.data.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.myapp.framework.data.persistance.model.AlbumsDao;
import com.example.myapp.framework.data.persistance.model.AlbumsRoomEntity;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

@Database(entities = {AlbumsRoomEntity.class}, version = 1)
public abstract class AlbumsDatabase extends RoomDatabase {
    public abstract AlbumsDao getAlbumDao();
}
