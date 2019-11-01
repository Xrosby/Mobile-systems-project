package com.example.wallshaveears.network;

import com.example.wallshaveears.database.entities.Traffic;

import java.util.ArrayList;

public interface INetworkDatabase
{
    /**
     * Takes the latest submitted traffic data for each app, and returns it in an ArrayList
     * @return List of latest traffic
     */
    ArrayList<Traffic> getLatestTraffic();


    /**
     * Takes an ArrayList of Traffic objects, and inserts their content into the database.
     * @param traffic an ArrayList of Traffic objects generated from the network.DataFethcer class
     */
    void submitNewData(ArrayList<Traffic> traffic);
}
