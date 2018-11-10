package com.example.myapp.di.components;

import com.example.myapp.di.modules.ActivityModule;
import com.example.myapp.scopes.ActivityScope;
import com.example.myapp.splash.SplashActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity mainActivity);
}
