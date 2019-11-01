package com.example.wallshaveears.network;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.wallshaveears.MainActivity;
import com.example.wallshaveears.database.entities.Traffic;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FetchDataTask extends JobService
{
    private INetworkDatabase networkDatabase = new TempClass();

    public FetchDataTask()
    {
    }

    @Override
    public boolean onStartJob(JobParameters params)
    {
        DataFetcher dataFetcher = new DataFetcher(this);
        ArrayList<Traffic> oldData = networkDatabase.getLatestTraffic();
        ArrayList<Traffic> recentData = dataFetcher.getRecentData(ConnectivityManager.TYPE_MOBILE, oldData);

        networkDatabase.submitNewData(recentData);

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
                .setMinimumLatency(TimeUnit.SECONDS.toMillis(5))
                .build());
    }
}
