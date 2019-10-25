package com.example.wallshaveears.ui.datastatistics.graphutil;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class AppDataComparator implements Comparator<String> {

    HashMap<String, RXTXWrapper> map = new HashMap<String, RXTXWrapper>();

    public AppDataComparator(Map<String, RXTXWrapper> map) {
        this.map.putAll(map);
    }

    @Override
    public int compare(String s1, String s2) {
        if (map.get(s1).getSum() >= map.get(s2).getSum()) {
            return 1;
        } else {
            return -1;
        }
    }
}