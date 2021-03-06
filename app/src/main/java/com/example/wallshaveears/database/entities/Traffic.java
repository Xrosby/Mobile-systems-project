package com.example.wallshaveears.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.wallshaveears.MainActivity;

import java.util.Date;

@Entity(tableName = "traffic_data")
public class Traffic {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "app_name")
    private String appName;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "received_data")
    private long rxBytes;

    @ColumnInfo(name = "transmitted_data")
    private long txBytes;

    private long rxDifference;

    private long txDifference;

    private long rxAccumulate;

    private long txAccumulate;

    @ColumnInfo(name = "bucket_expiration")
    private long bucketExp;

    @ColumnInfo(name = "application_id")
    private int appUid;

    @ColumnInfo(name = "type_id")
    private int typeId;

    public Traffic(String appName, long timestamp, long rxBytes, long txBytes, long rxDifference, long txDifference, long rxAccumulate, long txAccumulate, long bucketExp, int appUid, int typeId) {
        this.appName = appName;
        this.timestamp = timestamp;
        this.rxBytes = rxBytes;
        this.rxDifference = rxDifference;
        this.txDifference = txDifference;
        this.rxAccumulate = rxAccumulate;
        this.txAccumulate = txAccumulate;
        this.txBytes = txBytes;
        this.bucketExp = bucketExp;
        this.appUid = appUid;
        this.typeId = typeId;
    }

    @Ignore
    public Traffic()
    {

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

    public long getTimestamp() {
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

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public void setRxBytes(long rxBytes)
    {
        this.rxBytes = rxBytes;
    }

    public void setTxBytes(long txBytes)
    {
        this.txBytes = txBytes;
    }

    public void setRxDifference(long rxDifference)
    {
        this.rxDifference = rxDifference;
    }

    public void setTxDifference(long txDifference)
    {
        this.txDifference = txDifference;
    }

    public void setRxAccumulate(long rxAccumulate)
    {
        this.rxAccumulate = rxAccumulate;
    }

    public void setTxAccumulate(long txAccumulate)
    {
        this.txAccumulate = txAccumulate;
    }

    public void setBucketExp(long bucketExp)
    {
        this.bucketExp = bucketExp;
    }

    public void setAppUid(int appUid)
    {
        this.appUid = appUid;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public int getAppUid() {
        return appUid;
    }

    public int getTypeId() {
        return typeId;
    }

    public long getRxDifference() {
        return rxDifference;
    }

    public long getTxDifference() {
        return txDifference;
    }

    public long getRxAccumulate() {
        return rxAccumulate;
    }

    public long getTxAccumulate() {
        return txAccumulate;
    }

    public String toString() {
        return  "Name: " + appName + "\n" +
                "AppUid: " + appUid + "\n" +
                "Timestamp: " + new Date(timestamp).toString() + "\n" +
                "RxBytes: " + humanReadableByteCount(rxBytes, true) + "\n" +
                "TxBytes: " + humanReadableByteCount(txBytes, true) + "\n" +
                "RxDiff: " + humanReadableByteCount(rxDifference, true) + "\n" +
                "TxDiff: " + humanReadableByteCount(txDifference, true) + "\n" +
                "RxAccu: " + humanReadableByteCount(rxAccumulate, true) + "\n" +
                "TxAccu: " + humanReadableByteCount(txAccumulate, true) + "\n\n";

    }

    public static String humanReadableByteCount(long bytes, boolean si)
    {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}