package com.example.myapp.di;

import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.components.ActivityComponent;
import com.example.myapp.di.components.ApplicationComponent;
import com.example.myapp.di.components.DaggerApplicationComponent;
import com.example.myapp.di.modules.ActivityModule;
import com.example.myapp.di.modules.ApplicationModule;
import com.example.myapp.framework.di.modules.FrameworkApplicationModule;

public class AppDI {
    private static ApplicationComponent applicationComponent;

    private static ApplicationComponent getApplicationComponent(BaseActivity activity) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(activity.getApplication()))
                .frameworkApplicationModule(new FrameworkApplicationModule(activity.getApplication()))
                .build();
        return applicationComponent;
    }

    public static ActivityComponent getActivityComponent(BaseActivity activity) {
        return getApplicationComponent(activity).plus(new ActivityModule(activity));
    }

}
