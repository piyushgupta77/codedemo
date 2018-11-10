package com.example.myapp.framework.utils;

import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.Comparator;

import javax.inject.Inject;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

public class AlbumTitleComparator implements Comparator<AlbumsDataModel> {
    @Inject
    public AlbumTitleComparator(){
    }

    @Override
    public int compare(AlbumsDataModel albumsEntity, AlbumsDataModel albumToBecompared) {
        if (null != albumsEntity && StringUtils.isNullOrEmpty(albumsEntity.getTitle())
                && null != albumsEntity.getTitle() && StringUtils.isNullOrEmpty(albumToBecompared.getTitle())) {
            return albumsEntity.getTitle().compareTo(albumToBecompared.getTitle());
        } else {
            return FrameworkConstants.COMPARATOR_OBJECTS_EQUAL;
        }
    }
}
