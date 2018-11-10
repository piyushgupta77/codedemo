package com.example.myapp.framework.domain;

import android.support.annotation.NonNull;

import com.example.myapp.framework.base.BaseUseCase;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAlbumsDataUseCase extends BaseUseCase<List<AlbumsDataModel>> {

    private final IGetAlbumsDataRepository mGetAlbumsDataRepository;

    @Inject
    GetAlbumsDataUseCase(@NonNull final UseCaseComposer useCaseComposer,
                         @NonNull final IGetAlbumsDataRepository getWeatherDataRepository) {
        super(useCaseComposer);
        this.mGetAlbumsDataRepository = getWeatherDataRepository;
    }

    @Override
    protected Observable<List<AlbumsDataModel>> createObservable() {
        return mGetAlbumsDataRepository.getAlbumData();
    }
}
