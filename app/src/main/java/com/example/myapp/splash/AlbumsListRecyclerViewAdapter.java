package com.example.myapp.splash;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.Constants;
import com.example.myapp.R;
import com.example.myapp.network_library.model.AlbumsDataModel;

import java.util.List;

/**
 * Created by piyushgupta01 on 08-11-2018.
 */

public class AlbumsListRecyclerViewAdapter extends RecyclerView.Adapter<AlbumsListRecyclerViewAdapter.AlbumsViewHolder> {

    private final List<AlbumsDataModel> mAlbumsEntityList;

    AlbumsListRecyclerViewAdapter(List<AlbumsDataModel> albumsEntityList) {
        this.mAlbumsEntityList = albumsEntityList;
    }

    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_album_list, parent, false));
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        holder.mAlbumTitle.setText((position +1) + " " + mAlbumsEntityList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mAlbumsEntityList == null ? Constants.LIST_SIZE_ZERO : mAlbumsEntityList.size();
    }

    static class AlbumsViewHolder extends RecyclerView.ViewHolder {

        TextView mAlbumTitle;

        AlbumsViewHolder(View itemView) {
            super(itemView);
            mAlbumTitle = itemView.findViewById(R.id.tv_info);
        }

    }
}
