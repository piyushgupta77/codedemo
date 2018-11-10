package com.example.myapp.framework.presenter;

import android.support.annotation.NonNull;

import com.example.myapp.framework.base.BasePresenter;
import com.example.myapp.framework.domain.GetAlbumsDataUseCase;
import com.example.myapp.framework.utils.AlbumTitleComparator;

import java.util.Collections;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashView> {

    private final GetAlbumsDataUseCase mGetAlbumsDataUseCase;
    private final AlbumTitleComparator mAlbumTitleComparator;

    @Inject
    SplashPresenter(@NonNull final GetAlbumsDataUseCase getWeatherDataUseCase,
                    @NonNull final AlbumTitleComparator albumTitleComparator) {
        this.mGetAlbumsDataUseCase = getWeatherDataUseCase;
        this.mAlbumTitleComparator = albumTitleComparator;
    }

    public void fetchAlbumsData() {
        mGetAlbumsDataUseCase.executeUseCase()
                .compose(bindToLifeCycle())
                .doOnSubscribe(action -> {
                    if (view != null) {
                        view.showProgress(true);
                    }
                }).doOnTerminate(() -> {
                if (view != null) {
                    view.showProgress(false);
                }
                }).subscribe(albumsEntityList -> {
                    if (albumsEntityList != null) {
                        Collections.sort(albumsEntityList, mAlbumTitleComparator);
                        view.showSuccess(albumsEntityList);
                    } else {
                        //TODO get use case specific error message
                        view.showError("Empty list");
                    }
                }, throwable -> {
                    view.showError(throwable.getMessage());
                });
    }
}
