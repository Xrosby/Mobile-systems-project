package com.example.wallshaveears.network;

import android.app.job.JobParameters;
import android.app.job.JobService;

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

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params)
    {
        return false;
    }
}
