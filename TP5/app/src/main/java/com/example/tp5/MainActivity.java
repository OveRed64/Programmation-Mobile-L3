package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DetecteurDeClicSurRecycler{

    private MonRecyclerViewAdapter mAdapter;
    private static MainActivity mainActivity;

    public static int getImageID( String imageName ){
        return mainActivity.getResources().getIdentifier(imageName, "drawable", mainActivity.getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MonRecyclerViewAdapter(getDataSource());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setDetecteurDeClicSurRecycler(this);
    }

    private ArrayList<Data> getDataSource() {
        ArrayList<Data> results = new ArrayList<Data>();
        for (int i = 0; i < Data.PlanetsName.length; i++) {
            Data obj = new Data(
                    Data.PlanetsName[i],
                    "Rayon : " + Data.PlanetsDesc[i] + " km"
            );
            results.add(i, obj);
        }
        return results;
    }

    public void clicSurRecyclerItem(int position, View v) {
        CoordinatorLayout mcoordinatorLayout;
        mcoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Snackbar.make(mcoordinatorLayout, " Clic sur l'item " + position, Snackbar.LENGTH_LONG).show();
    }
}