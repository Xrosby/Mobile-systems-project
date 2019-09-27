package com.example.wallshaveears.ui.datastatistics.graphutil;

public class GraphData {

        private String appName;
        private long receivedBytes, transmittedBytes;;

        public GraphData(String appName, long receivedBytes, long transmittedBytes) {
            this.appName = appName;
            this.receivedBytes = receivedBytes;
            this.transmittedBytes = transmittedBytes;
        }

    public String getAppName() {
        return appName;
    }

    public long getReceivedBytes() {
        return receivedBytes;
    }

    public long getTransmittedBytes() {
        return transmittedBytes;
    }
}
