package com.example.wallshaveears.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "traffic_data",
        foreignKeys = {
                @ForeignKey(entity = NetworkType.class,
                        parentColumns = "id",
                        childColumns = "type_id",
                        onDelete = ForeignKey.NO_ACTION),

                @ForeignKey(entity = MonitoredApp.class,
                        parentColumns = "uid",
                        childColumns = "application_id",
                        onDelete = ForeignKey.NO_ACTION)
        },
        indices = {@Index(value = {"application_id"}), @Index(value = {"type_id"})})
public class Traffic {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    
    @ColumnInfo(name = "app_name")
    private String appName;

    @ColumnInfo(name = "timestamp")
    private Date timestamp;

    @ColumnInfo(name = "received_data")
    private long rxBytes;

    @ColumnInfo(name = "transmitted_data")
    private long txBytes;

    @ColumnInfo(name = "bucket_expiration")
    private long bucketExp;

    @ColumnInfo(name = "application_id")
    private int appUid;

    @ColumnInfo(name = "type_id")
    private int typeId;

    public Traffic(String appName ,Date timestamp, long rxBytes, long txBytes, long bucketExp, int appUid, int typeId) {
        this.timestamp = timestamp;
        this.rxBytes = rxBytes;
        this.txBytes = txBytes;
        this.bucketExp = bucketExp;
        this.appUid = appUid;
        this.typeId = typeId;
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public long getRxBytes() {
        return rxBytes;
    }

    public long getTxBytes() {
        return txBytes;
    }

    public long getBucketExp() {
        return bucketExp;
    }

    public int getAppUid() {
        return appUid;
    }

    public int getTypeId() {
        return typeId;
    }
}
