package com.example.hp.androidlivewallpaper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.androidlivewallpaper.Common.Common;
import com.example.hp.androidlivewallpaper.Database.Recents;
import com.example.hp.androidlivewallpaper.Interface.ItemClickListener;
import com.example.hp.androidlivewallpaper.ListWallpaper;
import com.example.hp.androidlivewallpaper.Model.WallpaperItem;
import com.example.hp.androidlivewallpaper.R;
import com.example.hp.androidlivewallpaper.ViewWallpaper;
import com.example.hp.androidlivewallpaper.viewHolder.ListWallpaperViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<ListWallpaperViewHolder> {
    private Context context;
    private List<Recents> recentsList;

    public MyRecyclerAdapter(Context context, List<Recents> recentsList) {
        this.context = context;
        this.recentsList = recentsList;
    }

    @NonNull
    @Override
    public ListWallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_wallpaper_item, viewGroup, false);
        int height = viewGroup.getMeasuredHeight() / 2;
        itemView.setMinimumHeight(height);
        return new ListWallpaperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final @NonNull ListWallpaperViewHolder holder,final int position) {
        Picasso.with(context).load(recentsList.get(position).getImageLink()).
                networkPolicy(NetworkPolicy.OFFLINE).
                into(holder.wallpaper, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(recentsList.get(position).getImageLink())
                                .error(R.drawable.ic_terrain_black_24dp).into(holder.wallpaper, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Log.e("ERROR_ENES", "Could not fetch image");
                            }
                        });

                    }
                });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(context,ViewWallpaper.class);
                WallpaperItem wallpaperItem=new WallpaperItem();
                wallpaperItem.setCategoryId(recentsList.get(position).getCategoryId());
                wallpaperItem.setImageLink(recentsList.get(position).getImageLink());
                Common.select_background=wallpaperItem;
                Common.select_background_key=recentsList.get(position).getKey();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentsList.size();
    }
}
