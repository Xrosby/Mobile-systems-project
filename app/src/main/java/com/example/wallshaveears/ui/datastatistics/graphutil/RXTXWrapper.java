package com.example.wallshaveears.ui.datastatistics.graphutil;

public class RXTXWrapper implements Comparable<RXTXWrapper> {

    private long recievedBytes;
    private long transmittedBytes;

    public RXTXWrapper(long recievedBytes, long transmittedBytes) {
        this.recievedBytes = recievedBytes;
        this.transmittedBytes = transmittedBytes;
    }

    public long getRecievedBytes() {
        return recievedBytes;
    }

    public long getSum() {
        return this.recievedBytes + transmittedBytes;
    }

    public long getTransmittedBytes() {
        return transmittedBytes;
    }

    @Override
    public int compareTo(RXTXWrapper o) {
            if (o.getSum() > this.getSum()) {
                return 1;
            } else if (o.getSum() < this.getSum()) {
                return 0;
            }
        return -1;
    }
}
