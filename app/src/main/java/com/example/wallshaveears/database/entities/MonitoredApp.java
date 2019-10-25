package com.example.wallshaveears.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "applications")
public class MonitoredApp {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "application_name")
    private String appName;

    @ColumnInfo(name = "application_description")
    private String appDesc;

    public MonitoredApp(String appName, String appDesc) {
        this.appName = appName;
        this.appDesc = appDesc;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppDesc() {
        return appDesc;
    }
}
