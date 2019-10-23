package com.example.wallshaveears.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wallshaveears.database.entities.NetworkType;

import java.util.List;

@Dao
public interface NetworkTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NetworkType networkType);

    @Query("SELECT * FROM 'network_types'")
    LiveData<List<NetworkType>> getAllNetworkTypes();
}

