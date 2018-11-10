package com.example.myapp.di.modules;

import android.app.Activity;

import com.example.myapp.framework.data.repositories.GetAlbumsDataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

}
