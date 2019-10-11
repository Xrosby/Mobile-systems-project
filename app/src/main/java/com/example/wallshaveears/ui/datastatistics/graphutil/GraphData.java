package com.example.wallshaveears.ui.datastatistics.graphutil;

public class GraphData {

    private int uid;
        private String appName;
        private long receivedBytes, transmittedBytes, timeStamp;


        public GraphData(int uid, String appName, long receivedBytes, long transmittedBytes, long timeStamp) {
            this.appName = appName;
            this.receivedBytes = receivedBytes;
            this.transmittedBytes = transmittedBytes;
        }

    public String getAppName() {
        return appName;
    }

    public int getUid() {
        return uid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getReceivedBytes() {
        return receivedBytes;
    }

    public long getTransmittedBytes() {
        return transmittedBytes;
    }




}
