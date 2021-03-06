package com.example.wallshaveears.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wallshaveears.database.entities.Traffic;

import java.util.List;

@Dao
public interface TrafficDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Traffic traffic);

    @Query("SELECT * FROM 'traffic_data'")
    LiveData<List<Traffic>> getAllTraffics();

    @Query("DELETE FROM 'traffic_data'")
    void deleteAll();

    @Query("SELECT * FROM 'traffic_data'")
    List<Traffic> getAllTrafficsSimple();

    @Query("SELECT *, MAX(timestamp) FROM 'traffic_data' GROUP BY application_id")
    List<Traffic> getLatestTraffic();
}
