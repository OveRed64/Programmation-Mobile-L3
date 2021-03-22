package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "preferences_file";

    private static MonRecyclerViewAdapter mAdapter;
    private static RecyclerView mRecyclerView;
    private static MainActivity mainActivity;
    private static AppDatabase db;

    public static int getImageID( String imageName ){
        return mainActivity.getResources().getIdentifier(imageName, "drawable", mainActivity.getPackageName());
    }

    public static MonRecyclerViewAdapter getMAdapter(){
        return mAdapter;
    }

    public static AppDatabase getDb() {
        return db;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetsDB").build();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadData();

        FloatingActionButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new NewPlanetDialogFragment();
                newFragment.show(getSupportFragmentManager(), "newPlanet");
            }
        });

    }

    private void loadData() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                PlanetDao planetDao = db.planetDao();

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planetDao);
                    settings.edit().putBoolean("is_data_loaded", false).apply();
                }

                List<Planet> planets = planetDao.getAll();

                mAdapter = new MonRecyclerViewAdapter(planets);
                mRecyclerView.setAdapter(mAdapter);
            }
        }).start();

    }

    private void initData(PlanetDao planetDao) {

        ArrayList<Planet> planets = new ArrayList<>();

        planets.add(new Planet("Mercure",4900));
        planets.add(new Planet("Venus",12000));
        planets.add(new Planet("Terre",12800));
        planets.add(new Planet("Mars",6800));
        planets.add(new Planet("Jupiter",144000));
        planets.add(new Planet("Saturne",120000));
        planets.add(new Planet("Uranus",52000));
        planets.add(new Planet("Neptune",50000));
        planets.add(new Planet("Pluton",2300));

        for (int index = 0; index < planets.size(); index++) {
            Planet planet = planets.get(index);
            planetDao.insert(planet);
        }
    }
}