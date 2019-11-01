package com.example.wallshaveears.network;

import com.example.wallshaveears.database.entities.Traffic;

import java.util.ArrayList;

public class TempClass implements INetworkDatabase
{
    @Override
    public ArrayList<Traffic> getLatestTraffic()
    {
        return null;
    }

    @Override
    public void submitNewData(ArrayList<Traffic> traffic)
    {

    }
}
