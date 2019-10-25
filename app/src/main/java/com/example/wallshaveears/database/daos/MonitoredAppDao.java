package com.example.wallshaveears.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wallshaveears.database.entities.MonitoredApp;

import java.util.List;

@Dao
public interface MonitoredAppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MonitoredApp monitoredApp);

    @Query("SELECT * FROM 'applications'")
    LiveData<List<MonitoredApp>> getAllMonitoredApps();
}
