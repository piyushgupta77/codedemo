package com.example.myapp.framework.di;

import android.app.Activity;

import com.example.myapp.framework.di.components.DaggerFrameworkApplicationComponent;
import com.example.myapp.framework.di.components.FrameworkApplicationComponent;
import com.example.myapp.framework.di.modules.FrameworkApplicationModule;

public class FrameworkDI {

    public static FrameworkApplicationComponent getApplicationComponent(Activity activity) {
        FrameworkApplicationComponent applicationComponent = DaggerFrameworkApplicationComponent.builder()
                .frameworkApplicationModule(new FrameworkApplicationModule(activity.getApplication()))
                .build();
        return applicationComponent;
    }

}
