package com.example.wallshaveears.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wallshaveears.database.daos.MonitoredAppDao;
import com.example.wallshaveears.database.daos.NetworkTypeDao;
import com.example.wallshaveears.database.daos.TrafficDao;
import com.example.wallshaveears.database.entities.MonitoredApp;
import com.example.wallshaveears.database.entities.NetworkType;
import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.database.helpers.DateConverter;

@Database(entities = {MonitoredApp.class, NetworkType.class, Traffic.class}, version = 3, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class TrafficDatabase extends RoomDatabase {

    private static volatile TrafficDatabase INSTANCE;

    public abstract TrafficDao trafficDao();
    public abstract MonitoredAppDao monitoredAppDao();
    public abstract NetworkTypeDao networkTypeDao();

    static TrafficDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TrafficDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TrafficDatabase.class, "traffic_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NetworkTypeDao networkDao;

        private PopulateDbAsyncTask(TrafficDatabase db) {
            networkDao = db.networkTypeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NetworkType networkType = new NetworkType("4G");
            networkDao.insert(networkType);
            return null;
        }
    }
}
