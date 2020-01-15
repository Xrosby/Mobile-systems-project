package com.example.wallshaveears.network;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.wallshaveears.MainActivity;
import com.example.wallshaveears.database.TrafficRepository;
import com.example.wallshaveears.database.entities.Traffic;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FetchDataTask extends JobService
{
    private INetworkDatabase networkDatabase;

    @Override
    public boolean onStartJob(JobParameters params)
    {
        if (networkDatabase == null)
        {
            networkDatabase = new TrafficRepository(this);
        }

        DataFetcher dataFetcher = new DataFetcher(this);
        ArrayList<Traffic> oldData = networkDatabase.getLatestTraffic();
        ArrayList<Traffic> recentData = dataFetcher.getRecentData(ConnectivityManager.TYPE_MOBILE, oldData);

        networkDatabase.submitNewData(recentData);
        Toast.makeText(this, "Fetching data...", Toast.LENGTH_LONG).show();

        scheduleRefresh();

        jobFinished(params, false);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params)
    {
        return false;
    }

    private void scheduleRefresh()
    {
        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(new JobInfo.Builder(MainActivity.jobSchedulerId,
                new ComponentName(this, FetchDataTask.class))
                .setMinimumLatency(TimeUnit.SECONDS.toMillis(Constants.JOB_INTERVAL))
                .build());
    }
}
