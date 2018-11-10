package com.example.myapp.network_library.di;

import com.example.myapp.network_library.di.components.DaggerNetworkApplicationComponent;
import com.example.myapp.network_library.di.components.NetworkApplicationComponent;
import com.example.myapp.network_library.di.modules.NetworkApplicationModule;

public class NetworkDI {

    public static NetworkApplicationComponent getApplicationComponent() {
        NetworkApplicationComponent applicationComponent = DaggerNetworkApplicationComponent.builder()
                .networkApplicationModule(new NetworkApplicationModule())
                .build();
        return applicationComponent;
    }

}
