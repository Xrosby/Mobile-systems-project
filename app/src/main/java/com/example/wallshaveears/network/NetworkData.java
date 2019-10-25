package com.example.wallshaveears.network;

import java.util.Date;

public class NetworkData
{
    private long txBytes;
    private long rxBytes;
    private String appName;
    private long timeStamp;
    private int uid;
    private long bucketExpiration;
    private long rxAccumulator;
    private long txAccumulator;
    private long rxDifference;
    private long txDifference;

    public long getBucketExpiration()
    {
        return this.bucketExpiration;
    }

    public void setBucketExpiration(long bucketExpiration)
    {
        this.bucketExpiration = bucketExpiration;
    }

    public long getTxBytes()
    {
        return txBytes;
    }

    public void setTxBytes(long txBytes)
    {
        this.txBytes = txBytes;
    }

    public long getRxBytes()
    {
        return rxBytes;
    }

    public void setRxBytes(long rxBytes)
    {
        this.rxBytes = rxBytes;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    @Override
    public String toString()
    {
        return  "Name: " + appName + "\n" +
                "Uid: " + uid + "\n" +
                "Timestamp: " + new Date(timeStamp).toString() + "\n" +
                "RxBytesUsed: " + humanReadableByteCount(rxDifference, true) + "\n" +
                "TxBytesUsed: " + humanReadableByteCount(txDifference, true) + "\n\n";
    }

    public long getRxAccumulator()
    {
        return rxAccumulator;
    }

    public void setRxAccumulator(long rxAccumulator)
    {
        this.rxAccumulator = rxAccumulator;
    }

    public long getRxDifference()
    {
        return rxDifference;
    }

    public void setRxDifference(long rxDifference)
    {
        this.rxDifference = rxDifference;
    }

    public long getTxDifference()
    {
        return txDifference;
    }

    public void setTxDifference(long txDifference)
    {
        this.txDifference = txDifference;
    }

    public long getTxAccumulator()
    {
        return txAccumulator;
    }

    public void setTxAccumulator(long txAccumulator)
    {
        this.txAccumulator = txAccumulator;
    }

    private String humanReadableByteCount(long bytes, boolean si)
    {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
