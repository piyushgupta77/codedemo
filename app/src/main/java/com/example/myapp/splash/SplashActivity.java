package com.example.myapp.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.presenter.SplashPresenter;
import com.example.myapp.framework.presenter.SplashView;
import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    private static final String TAG = SplashActivity.class.getCanonicalName();

    @Inject
    SplashPresenter mSplashPresenter;

    private TextView mEmptyMsgView;
    private RecyclerView mAlbumListRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDI.getActivityComponent(this).inject(this);
        mEmptyMsgView = findViewById(R.id.tv_msg_empty);
        mAlbumListRecyclerView = findViewById(R.id.rv_album_list);
        mProgressBar = findViewById(R.id.progressBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAlbumListRecyclerView.setLayoutManager(linearLayoutManager);
        mAlbumListRecyclerView.addItemDecoration(new DividerItemDecoration(mAlbumListRecyclerView.getContext(), linearLayoutManager.getOrientation()));
        mSplashPresenter.bind(this);
        mSplashPresenter.fetchAlbumsData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.unbind();
    }

    @Override
    public void showSuccess(@NonNull List<AlbumsDataModel> albumsEntity) {
        Log.d(TAG, "success " + albumsEntity.size());
        mAlbumListRecyclerView.setAdapter(new AlbumsListRecyclerViewAdapter(albumsEntity));
        mAlbumListRecyclerView.setVisibility(View.VISIBLE);
        mEmptyMsgView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "failure " + message);
        mAlbumListRecyclerView.setVisibility(View.GONE);
        mEmptyMsgView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
            mEmptyMsgView.setVisibility(View.GONE);
            mAlbumListRecyclerView.setVisibility(View.GONE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyMsgView.setVisibility(View.VISIBLE);
            mAlbumListRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
