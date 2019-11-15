package com.example.wallshaveears.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.wallshaveears.MainActivity;
import com.example.wallshaveears.database.daos.TrafficDao;
import com.example.wallshaveears.database.entities.NetworkType;
import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.network.INetworkDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class TrafficRepository implements INetworkDatabase, LifecycleOwner {

    private TrafficDatabase trafficDb;
    private Context context;

    public TrafficRepository(Context context) {
        trafficDb = TrafficDatabase.getDatabase(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }

    @Override
    public ArrayList<Traffic> getLatestTraffic() {

        ArrayList<Traffic> latestTraffic = new ArrayList<>();
        AppCompatActivity appContext = null;

        if (context instanceof AppCompatActivity)
        {
            appContext = (AppCompatActivity) context;
        }
        else
        {
            return latestTraffic;
        }
        trafficDb.trafficDao().getAllTraffics().observe(appContext, new Observer<List<Traffic>>() {
            @Override
            public void onChanged(@Nullable final List<Traffic> traffics) {
                if(traffics.size() == 0) return;
                latestTraffic.add(traffics.get(traffics.size() - 1));
            }
        });
        return latestTraffic;
    }

    @Override
    public void submitNewData(ArrayList<Traffic> traffics) {
        ListIterator iter = traffics.listIterator();

        while (iter.hasNext()) {
            Integer index = iter.nextIndex();
            iter.next();

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    trafficDb.trafficDao().insert(traffics.get(index));
                    return null;
                }
            }.execute();
        }
    }

    public void deleteAllTraffics() {
        Thread thread = new Thread()
        {
            public void run()
            {
                trafficDb.trafficDao().deleteAll();
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<String> getAllForTest() {

        List<String> printOutDb = new ArrayList<>();
        List<Traffic> list = new ArrayList<>();
        Thread thread = new Thread()
        {
            public void run()
            {
                list.addAll(trafficDb.trafficDao().getAllTrafficsSimple());

                if(list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String dbRecord = "";
                        dbRecord += list.get(i).getId() + "," + list.get(i).getAppName() + "," + list.get(i).getTimestamp() + "," + list.get(i).getRxBytes() + "," + list.get(i).getTxBytes() + "," + list.get(i).getRxDifference() + "," + list.get(i).getTxDifference() + "," + list.get(i).getRxAccumulate() + "," + list.get(i).getTxAccumulate() + "," + list.get(i).getBucketExp() + "," + list.get(i).getAppUid() + "," + list.get(i).getTypeId();
                        printOutDb.add(dbRecord);
                    }
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //List<Traffic> list = trafficDb.trafficDao().getAllTrafficsSimple();
        return printOutDb;
    }

    public List<Traffic> getAllTraffic() {
        List<Traffic> list = new ArrayList<>();
        Thread thread = new Thread()
        {
            public void run()
            {
                list.addAll(trafficDb.trafficDao().getAllTrafficsSimple());

            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //List<Traffic> list = trafficDb.trafficDao().getAllTrafficsSimple();

        return list;
    }
}
