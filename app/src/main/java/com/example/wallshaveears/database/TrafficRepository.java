package com.example.wallshaveears.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wallshaveears.database.daos.TrafficDao;
import com.example.wallshaveears.database.entities.Traffic;

import java.util.List;

public class TrafficRepository {

    private TrafficDatabase trafficDb;
    private TrafficDao trafficDao;

    public TrafficRepository(Context context) {

        trafficDb = TrafficDatabase.getDatabase(context);
        trafficDao = trafficDb.trafficDao();
    }

    // Traffic entity's methods

    public LiveData<List<Traffic>> getLiveTraffics() { return trafficDb.trafficDao().getAllTraffics(); }

    public void insertTraffic(final Traffic traffic) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                trafficDb.trafficDao().insert(traffic);
                return null;
            }
        }.execute();
    }
}
