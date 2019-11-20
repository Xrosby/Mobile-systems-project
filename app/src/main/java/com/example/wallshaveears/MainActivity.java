package com.example.wallshaveears;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.wallshaveears.database.TrafficRepository;
import com.example.wallshaveears.database.entities.NetworkType;
import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.graphutil.tests.DummyDataGenerator;
import com.example.wallshaveears.network.Constants;
import com.example.wallshaveears.network.DataFetcher;
import com.example.wallshaveears.network.FetchDataTask;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static int jobSchedulerId = 69;
    private DataFetcher dataFetcher = new DataFetcher(this);
    private Button jobButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_stats, R.id.nav_permissions,
                R.id.nav_mydata)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        jobButton = findViewById(R.id.jobButton);
//        jobButton.setOnClickListener(view ->
//        {
//            stopJobScheduler();
//        });

        if(!specialPermissionGranted())
        {
            askForSpecialPermission();

        }
        requestPhonePermissions();

        startJobScheuler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void askForSpecialPermission()
    {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }

    private boolean specialPermissionGranted()
    {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        if (mode == AppOpsManager.MODE_ALLOWED)
        {
            return true;
        }
        return false;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //startJobScheuler();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //stopJobScheduler();
    }

    public void startJobScheuler()
    {
        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(new JobInfo.Builder(jobSchedulerId,
                new ComponentName(this, FetchDataTask.class))
                .setMinimumLatency(TimeUnit.SECONDS.toMillis(Constants.JOB_INTERVAL))
                .build());
    }

    private void stopJobScheduler()
    {
        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.cancel(jobSchedulerId);
    }

    private void requestPhonePermissions()
    {
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 20);
        }
    }
}
