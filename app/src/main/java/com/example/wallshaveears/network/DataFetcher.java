package com.example.wallshaveears.network;

import android.annotation.SuppressLint;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.telephony.TelephonyManager;

import com.example.wallshaveears.database.entities.Traffic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataFetcher
{
    private Context context;
    private long timeBeganFetching = 0;
    private final long NO_BUCKET_AVAILABLE = 0;

    public DataFetcher(Context context)
    {
        this.context = context;
    }

    /**
     *
     * @param connectivityType use ConnectivityManager.TYPE_MOBILE or TYPE_WIFI
     * @param oldData supplied if you have old data. Leave null if no old data exists
     * @return list of every app and its data usage
     */
    public ArrayList<Traffic> getRecentData(int connectivityType, ArrayList<Traffic> oldData)
    {
        // run through every application on the phone
        // get in/out data from every app, and save in network data object


        // ********************************************************************************************************
        // ********************************************************************************************************

        timeBeganFetching = System.currentTimeMillis();

        NetworkStatsManager manager = context.getSystemService(NetworkStatsManager.class);
        ArrayList<Traffic> listOfNetworkData = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        long startTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(8);
        long endTime = calendar.getTimeInMillis();

        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        @SuppressLint("MissingPermission") String subscriberId = tManager.getSubscriberId();

        // ********************************************************************************************************
        // ********************************************************************************************************




        for (ResolveInfo resolve : getAllAppsInfoWithIntent())
        {
            NetworkStats stats = manager.queryDetailsForUid(connectivityType, subscriberId,
                    startTime, endTime, resolve.activityInfo.applicationInfo.uid);


            NetworkStats.Bucket bucket = new NetworkStats.Bucket();

            // performing this while loop should leave the "bucket" variable containing the latest bucket.
            while (stats.hasNextBucket())
            {
                stats.getNextBucket(bucket);
            }
            if (bucket.getEndTimeStamp() != NO_BUCKET_AVAILABLE)
            {
                extractDataFromBucket(listOfNetworkData, resolve, bucket);
            }
        }
        //Log.e("abetest", "extracted from: " + listOfNetworkData.size());
        // the new data gathered is subtracted the usage of older data. This way we can update every 5 minutes.
        return filterForRecentUsage(listOfNetworkData, oldData);
    }



    private void extractDataFromBucket(ArrayList<Traffic> newData, ResolveInfo resolveInfo, NetworkStats.Bucket bucket)
    {
        Traffic data = new Traffic();

        data.setAppName(context.getPackageManager().getApplicationLabel(resolveInfo.activityInfo.applicationInfo).toString());

        data.setRxBytes(bucket.getRxBytes());
        data.setTxBytes(bucket.getTxBytes());

        data.setTimestamp(timeBeganFetching);
        data.setBucketExp(bucket.getEndTimeStamp());

        data.setId(resolveInfo.activityInfo.applicationInfo.uid);

        newData.add(data);
    }

    private ArrayList<Traffic> filterForRecentUsage(ArrayList<Traffic> newData, ArrayList<Traffic> oldData)
    {
        if (oldData == null)
        {
            return newData;
        }

        //ArrayList<NetworkData> updatedData = new ArrayList<>();

        for (Traffic nData : newData)
        {
            for (Traffic oData : oldData)
            {
                if (nData.getId() == oData.getId())
                {
                    nData.setRxDifference(nData.getRxBytes() - oData.getRxBytes());
                    nData.setTxDifference(nData.getTxBytes() - oData.getTxBytes());

                    nData.setRxAccumulate(nData.getRxDifference() + oData.getRxAccumulate());
                    nData.setTxAccumulate(nData.getTxDifference() + oData.getTxAccumulate());
                    //updatedData.add(subtractData(nData, oData));
                }
            }
        }
        return newData;
    }

    private NetworkData subtractData(NetworkData newData, NetworkData oldData)
    {
        // this method creates a new NetworkData object,
        // and inserts the info from the newData argument.
        // then it takes the difference of the data from the oldData object and the newData object.
        // this gets us the difference in data usage from last time.
        // this method should ONLY be used when we are dealing with data coming from the same bucket.

        NetworkData returnData = new NetworkData();

        returnData.setUid(newData.getUid());
        returnData.setAppName(newData.getAppName());
        returnData.setTimeStamp(newData.getTimeStamp());
        returnData.setBucketExpiration(newData.getBucketExpiration());

        returnData.setRxBytes(newData.getRxBytes() - oldData.getRxBytes());
        returnData.setTxBytes(newData.getRxBytes() - oldData.getRxBytes());

        return returnData;
    }

    private List<ResolveInfo> getAllAppsInfoWithIntent()
    {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return context.getPackageManager().queryIntentActivities(i, 0);
    }

    public ArrayList<Traffic> init(int connectivityType)
    {
        return getRecentData(connectivityType, null);
    }
}
