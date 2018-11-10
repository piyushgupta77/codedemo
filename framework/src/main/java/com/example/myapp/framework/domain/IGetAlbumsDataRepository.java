package com.example.myapp.framework.domain;

import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

public interface IGetAlbumsDataRepository {
    Observable<List<AlbumsDataModel>> getAlbumData();
}
