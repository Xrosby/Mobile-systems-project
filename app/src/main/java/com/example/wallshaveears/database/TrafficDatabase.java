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

import java.sql.Array;
import java.sql.Date;

@Database(entities = {MonitoredApp.class, NetworkType.class, Traffic.class}, version = 1, exportSchema = false)
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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback(){

        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TrafficDao mTrafficDao;
        private final NetworkTypeDao mNetworkTypeDao;

        PopulateDbAsync(TrafficDatabase db) {
            mTrafficDao = db.trafficDao();
            mNetworkTypeDao = db.networkTypeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}
