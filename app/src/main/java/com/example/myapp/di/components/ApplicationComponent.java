package com.example.myapp.di.components;

import com.example.myapp.di.modules.ActivityModule;
import com.example.myapp.di.modules.ApplicationModule;
import com.example.myapp.framework.di.modules.FrameworkApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, FrameworkApplicationModule.class})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule payActivityModule);
}