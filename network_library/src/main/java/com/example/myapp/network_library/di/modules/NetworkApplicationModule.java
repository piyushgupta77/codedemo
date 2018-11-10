package com.example.myapp.network_library.di.modules;

import com.example.myapp.network_library.network.client.NetworkClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkApplicationModule {

    public NetworkApplicationModule() {
    }

    @Provides
    @Singleton
    NetworkClient provideNetworkClient() {
        return new NetworkClient();
    }
}
