package com.example.myapp.framework.di.components;

import com.example.myapp.framework.di.modules.FrameworkApplicationModule;
import com.example.myapp.network_library.di.modules.NetworkApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FrameworkApplicationModule.class, NetworkApplicationModule.class})
public interface FrameworkApplicationComponent {
}