package com.example.myapp.framework.presenter;

import com.example.myapp.framework.domain.GetAlbumsDataUseCase;
import com.example.myapp.network_library.model.AlbumsDataModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SplashPresenterTest {

    @InjectMocks
    SplashPresenter mSplashPresenter;

    @Mock
    SplashView mSplashView;

    @Mock
    List<AlbumsDataModel> mAlbumsDataModels;

    @Mock
    GetAlbumsDataUseCase mGetAlbumsDataUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mSplashPresenter.bind(mSplashView);
    }

    @Test
    public void testGetAlbumsSuccessResponseEmptyResponse() {
        when(mGetAlbumsDataUseCase.executeUseCase()).thenReturn(Observable.just(mAlbumsDataModels));
        mSplashPresenter.fetchAlbumsData();
        verify(mSplashView).showSuccess(mAlbumsDataModels);
    }

    @Test
    public void testGetAlbumsError() {
        Throwable throwable = Mockito.mock(Throwable.class);
        when(throwable.getMessage()).thenReturn("error");
        when(mGetAlbumsDataUseCase.executeUseCase()).thenReturn(Observable.error(throwable));
        mSplashPresenter.fetchAlbumsData();
        verify(mSplashView).showError("error");
    }

}
