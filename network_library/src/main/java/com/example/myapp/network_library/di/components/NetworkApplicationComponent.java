package com.example.myapp.network_library.di.components;

import com.example.myapp.network_library.di.modules.NetworkApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkApplicationModule.class)
public interface NetworkApplicationComponent {
}