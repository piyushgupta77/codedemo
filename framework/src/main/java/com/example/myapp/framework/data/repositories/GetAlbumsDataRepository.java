package com.example.myapp.framework.data.repositories;

import android.support.annotation.NonNull;

import com.example.myapp.framework.data.persistance.model.AlbumsDao;
import com.example.myapp.framework.data.persistance.model.AlbumsRoomEntity;
import com.example.myapp.framework.domain.IGetAlbumsDataRepository;
import com.example.myapp.framework.utils.FrameworkConstants;
import com.example.myapp.network_library.model.AlbumsDataModel;
import com.example.myapp.network_library.network.api.GetAlbumsApi;
import com.example.myapp.network_library.network.client.NetworkClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAlbumsDataRepository implements IGetAlbumsDataRepository {

    private final NetworkClient networkClient;
    private AlbumsDao albumDao;

    @Inject
    GetAlbumsDataRepository(@NonNull final NetworkClient networkClient,
                            @NonNull final AlbumsDao albumDao) {
        this.networkClient = networkClient;
        this.albumDao = albumDao;
    }

    @Override
    public Observable<List<AlbumsDataModel>> getAlbumData() {
        // defer observable until it is subscribed. This will database queries (using albumDao) will
        // execute only when someone has subscribed to the observable and not before that.
        return Observable.defer(() -> {
            // check if data persists in database, if yes then read data from DB using AlbumDao or
            // else fetch it from API
            if (albumDao.getCount() > FrameworkConstants.SIZE_ZERO) {
                return Observable.just(albumDao.getAll()).map(this::mapDaoToDataModel);
            } else {
                // fetch data from API and store in database using storeData()
                return networkClient.create(GetAlbumsApi.class).getAlbumsData()
                        .map(this::storeData);
            }
        });
    }

    /**
     * This method will store data in database using Room's {@link AlbumsDao}
     *
     * @param albumsEntities data list to be inserted
     * @return input list for reference. This can be used in Observable's map operator
     */
    private List<AlbumsDataModel> storeData(List<AlbumsDataModel> albumsEntities) {
        albumDao.insertAll(mapToRoomEntity(albumsEntities));
        return albumsEntities;
    }

    /**
     * This method will map Room's Dao model classes to view model classes. This will ensure
     * separation of Room's implementation and necessary dependencies from View(or app module)
     *
     * @param albumsRoomEntityList List of album entity as used in Room database
     * @return List of album view models to be used by UI and app module
     */
    private List<AlbumsDataModel> mapDaoToDataModel(List<AlbumsRoomEntity> albumsRoomEntityList) {
        List<AlbumsDataModel> albumsDataModelList = null;
        if (null != albumsRoomEntityList) {
            albumsDataModelList = new ArrayList<>(albumsRoomEntityList.size());
            for (int index = FrameworkConstants.SIZE_ZERO; index < albumsRoomEntityList.size(); index++) {
                AlbumsRoomEntity albumsModel = albumsRoomEntityList.get(index);
                AlbumsDataModel albumsDataModel = new AlbumsDataModel();
                albumsDataModel.setAlbumId(albumsModel.getAlbumId());
                albumsDataModel.setTitle(albumsModel.getTitle());
                albumsDataModel.setUserId(albumsModel.getUserId());
                albumsDataModelList.add(albumsDataModel);
            }
        }
        return albumsDataModelList;
    }

    private List<AlbumsRoomEntity> mapToRoomEntity(List<AlbumsDataModel> albumsDataModelList) {
        List<AlbumsRoomEntity> albumsRoomEntityList = null;
        if (null != albumsDataModelList) {
            albumsRoomEntityList = new ArrayList<>(albumsDataModelList.size());
            for (int index = FrameworkConstants.SIZE_ZERO; index < albumsDataModelList.size(); index++) {
                AlbumsDataModel albumsDataModel = albumsDataModelList.get(index);
                AlbumsRoomEntity albumsRoomEntity = new AlbumsRoomEntity();
                albumsRoomEntity.setAlbumId(albumsDataModel.getAlbumId());
                albumsRoomEntity.setTitle(albumsDataModel.getTitle());
                albumsRoomEntity.setUserId(albumsDataModel.getUserId());
                albumsRoomEntityList.add(albumsRoomEntity);
            }
        }
        return albumsRoomEntityList;
    }
}
