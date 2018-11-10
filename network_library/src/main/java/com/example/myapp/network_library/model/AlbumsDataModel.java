
package com.example.myapp.network_library.model;

import com.squareup.moshi.Json;

public class AlbumsDataModel {
    @Json(name = "userId")
    private int userId;

    @Json(name = "albumId")
    private int albumId;

    @Json(name = "title")
    private String title;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }


}
