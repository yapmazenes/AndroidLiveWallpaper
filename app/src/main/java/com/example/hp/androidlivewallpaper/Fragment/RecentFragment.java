package com.example.hp.androidlivewallpaper.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.androidlivewallpaper.Adapter.MyRecyclerAdapter;
import com.example.hp.androidlivewallpaper.Database.DataSource.RecentRepository;
import com.example.hp.androidlivewallpaper.Database.LocalDatabase.LocalDatabase;
import com.example.hp.androidlivewallpaper.Database.LocalDatabase.RecentsDataSource;
import com.example.hp.androidlivewallpaper.Database.Recents;
import com.example.hp.androidlivewallpaper.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    private static RecentFragment INSTANCE = null;

    RecyclerView recyclerView;

    Context context;

    public RecentFragment() {
    }

    List<Recents> recentsList;
    MyRecyclerAdapter adapter;

    //Room Database
    CompositeDisposable compositeDisposable;
    RecentRepository recentRepository;

    @SuppressLint("ValidFragment")
    public RecentFragment(Context context) {
        this.context = context;
        //Init RoomDatabase
        compositeDisposable = new CompositeDisposable();
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        recentRepository = RecentRepository.getInstance(RecentsDataSource.getInstance(localDatabase.recentDAO()));
    }


    public static RecentFragment getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new RecentFragment(context);
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        recyclerView = v.findViewById(R.id.recycler_recent);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recentsList = new ArrayList<>();
        adapter = new MyRecyclerAdapter(context, recentsList);
        recyclerView.setAdapter(adapter);

        LoadRecents();
        return v;
    }

    private void LoadRecents() {
        Disposable disposable = recentRepository.getAllRecents().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Recents>>() {
            @Override
            public void accept(List<Recents> recents) throws Exception {
                onGetAllRecentsSuccess(recents);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("ERROR", throwable.getMessage());
            }
        });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void onGetAllRecentsSuccess(List<Recents> recents) {
        recentsList.clear();
        recentsList.addAll(recents);
        adapter.notifyDataSetChanged();
    }


}
