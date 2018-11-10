package com.example.myapp.framework.presenter;

import android.support.annotation.NonNull;

import com.example.myapp.framework.base.BaseView;
import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

public interface SplashView extends BaseView{
    void showSuccess(@NonNull List<AlbumsDataModel> weatherMainEntity);

    void showError(String message);

    void showProgress(boolean show);
}
