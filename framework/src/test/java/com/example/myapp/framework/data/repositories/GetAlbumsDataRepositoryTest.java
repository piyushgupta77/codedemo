package com.example.myapp.framework.data.repositories;

import com.example.myapp.framework.data.persistance.model.AlbumsDao;
import com.example.myapp.framework.data.persistance.model.AlbumsRoomEntity;
import com.example.myapp.network_library.model.AlbumsDataModel;
import com.example.myapp.network_library.network.api.GetAlbumsApi;
import com.example.myapp.network_library.network.client.NetworkClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class GetAlbumsDataRepositoryTest {

    @InjectMocks
    private GetAlbumsDataRepository mGetWeatherDataRepository;

    @Mock
    private NetworkClient mNetworkClient;

    @Mock
    private AlbumsDao mAlbumDao;

    @Mock
    private GetAlbumsApi mGetAlbumsApi;

    private List<AlbumsDataModel> mAlbumsDataModels;

    private List<AlbumsRoomEntity> mAlbumsRoomEntities;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mAlbumsDataModels = getDummyAlbumsDataModels();
        mAlbumsRoomEntities = getDummyAlbumsRoomEntities();
    }

    @Test
    public void testGetDataFromNetwork() {
        when(mAlbumDao.getCount()).thenReturn(0);
        when(mNetworkClient.create(GetAlbumsApi.class)).thenReturn(mGetAlbumsApi);
        when(mGetAlbumsApi.getAlbumsData()).thenReturn(Observable.just(mAlbumsDataModels));
        mGetWeatherDataRepository.getAlbumData().subscribe();
        //verify that data downloaded from API call is triggered to be stored into database
        verify(mAlbumDao).insertAll(anyListOf(AlbumsRoomEntity.class));
    }

    @Test
    public void testGetDataFromLocal() {
        when(mAlbumDao.getCount()).thenReturn(1);
        when(mAlbumDao.getAll()).thenReturn(mAlbumsRoomEntities);
        mGetWeatherDataRepository.getAlbumData().subscribe();
        //verify that there is no calls to network via NetworkClient
        verifyNoMoreInteractions(mNetworkClient);
    }

    private List<AlbumsDataModel> getDummyAlbumsDataModels() {
        List<AlbumsDataModel> albumsDataModelList = new ArrayList<>();
        AlbumsDataModel albumsDataModel = new AlbumsDataModel();
        albumsDataModel.setAlbumId(1);
        albumsDataModel.setTitle("title");
        albumsDataModel.setUserId(1001);
        albumsDataModelList.add(albumsDataModel);
        return albumsDataModelList;
    }

    private List<AlbumsRoomEntity> getDummyAlbumsRoomEntities() {
        List<AlbumsRoomEntity> albumsRoomEntityArrayList = new ArrayList<>();
        AlbumsRoomEntity albumsRoomEntity = new AlbumsRoomEntity();
        albumsRoomEntity.setAlbumId(1);
        albumsRoomEntity.setTitle("title");
        albumsRoomEntity.setUserId(1001);
        albumsRoomEntityArrayList.add(albumsRoomEntity);
        return albumsRoomEntityArrayList;
    }
}
