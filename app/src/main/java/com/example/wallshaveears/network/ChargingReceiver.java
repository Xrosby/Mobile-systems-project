package com.example.wallshaveears.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ChargingReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Something happened", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
        {
            //serverApiAccess.uploadData(data);
            Toast.makeText(context, "Uploading database data to server...", Toast.LENGTH_SHORT).show();
            Log.d("Status", "");
        }
        else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
        {
            //serverApiAccess.stopUpload();
            Toast.makeText(context, "Stopping upload to server...", Toast.LENGTH_SHORT).show();
            Log.d("Status", "Stopping upload to server...");
        }
    }
}
