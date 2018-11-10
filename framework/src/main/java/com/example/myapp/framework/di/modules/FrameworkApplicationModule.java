package com.example.myapp.framework.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.myapp.framework.base.AndroidSchedulerUseCaseComposer;
import com.example.myapp.framework.base.UseCaseComposer;
import com.example.myapp.framework.data.persistance.AlbumsDatabase;
import com.example.myapp.framework.data.persistance.model.AlbumsDao;
import com.example.myapp.framework.data.repositories.GetAlbumsDataRepository;
import com.example.myapp.framework.domain.IGetAlbumsDataRepository;
import com.example.myapp.framework.utils.FrameworkConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FrameworkApplicationModule {
    private final AlbumsDatabase albumsDatabase;
    private Application application;

    public FrameworkApplicationModule(Application application) {
        this.application= application;
        albumsDatabase = Room.databaseBuilder(application, AlbumsDatabase.class, FrameworkConstants.DATABASE_FILE_NAME).build();
    }

    @Provides
    public Context provideContext(){
        return application.getApplicationContext();
    }

    @Provides
    public UseCaseComposer provideUseCaseComposer(){
        return new AndroidSchedulerUseCaseComposer();
    }

    @Provides
    public IGetAlbumsDataRepository provideGetAlbumDataRepository(GetAlbumsDataRepository getAlbumsDataRepository) {
        return getAlbumsDataRepository;
    }

    @Provides
    @Singleton
    AlbumsDatabase provideAlbumsDatabase() {
        return albumsDatabase;
    }

    @Provides
    @Singleton
    AlbumsDao provideAlbumsDao() {
        return albumsDatabase.getAlbumDao();
    }
}
