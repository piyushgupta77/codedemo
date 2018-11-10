package com.example.myapp.network_library.network.api;

import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetAlbumsApi {

    @GET("albums")
    Observable<List<AlbumsDataModel>> getAlbumsData();
}
