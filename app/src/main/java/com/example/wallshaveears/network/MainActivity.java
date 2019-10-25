package com.example.wallshaveears.network;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private TextView textName, textSent, textReceived;
    private Button fetchButton;
    private ArrayList<NetworkData> oldData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGui();

        if (!specialPermissionGranted())
        {
            askForSpecialPermission();
        }
        DataFetcher fetcher = new DataFetcher(this);
        oldData = fetcher.init(ConnectivityManager.TYPE_MOBILE);
    }


    private void initGui()
    {
        textName = findViewById(R.id.appName);
        textSent = findViewById(R.id.sentBytes);
        textReceived = findViewById(R.id.receivedBytes);
        fetchButton = findViewById(R.id.fetchButton);

        fetchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DataFetcher fetcher = new DataFetcher(MainActivity.this);
                ArrayList<NetworkData> dataUsage = fetcher.getRecentData(ConnectivityManager.TYPE_MOBILE, oldData);

                for(NetworkData data : dataUsage)
                {
                    //if (data.getAppName().equalsIgnoreCase("youtube"))
                    {
                        Log.e("Abetest", data.toString());
                    }
                }
                Log.e("Abetest", dataUsage.size() + " apps found...");
                oldData = dataUsage;
            }
        });
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

    public static String humanReadableByteCount(long bytes, boolean si)
    {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
