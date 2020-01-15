package com.example.wallshaveears.network;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.wallshaveears.database.TrafficRepository;
import com.example.wallshaveears.database.entities.Traffic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Date;

public class ChargingReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Something happened", Toast.LENGTH_SHORT).show();
        if ((intent.getAction().equals(Intent.ACTION_POWER_CONNECTED) && Integer.parseInt(new Date().toString().split(" ")[3].split(":")[0]) < 7))
        {
            sendTrafficToServer(context);
            //Toast.makeText(context, "Uploading database data to server...", Toast.LENGTH_SHORT).show();
            Log.d("Status", "");
        }
        else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
        {
            //serverApiAccess.stopUpload();
            Toast.makeText(context, "Stopping upload to server...", Toast.LENGTH_SHORT).show();
            Log.d("Status", "Stopping upload to server...");
        }
    }




























    @SuppressLint("StaticFieldLeak")
    public void sendTrafficToServer(Context context)
    {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... voids)
            {

                //Date date = new Date();
                final String fileName = "mobile";
                StringBuilder output = new StringBuilder();

                TrafficRepository database = new TrafficRepository(context);
                List<Traffic> allTraffic = database.getAllTraffic();
                for (int i = 0; i < allTraffic.size(); i++)
                {
                    output.append(allTraffic.get(i).toString());
                    if (i % 5 == 0)
                    {
                        sendStringToServer(fileName, output.toString());
                        output = new StringBuilder();
                    }
                }
                sendStringToServer(fileName, output.toString());
                //Log.e("outout", output.toString());
                //Toast.makeText(context, "Appending " + allTraffic.size(), Toast.LENGTH_SHORT).show();


                //final String content = label + ":" + date.getTime();


                return null;
            }
        }.execute();

    }

    private void sendStringToServer(String fileName, String content)
    {
        try
        {
            final String appendUrl = "http://klevang.dk:8080/append?file=" + fileName + "&content=" + URLEncoder.encode(content, StandardCharsets.UTF_8.toString());
            Log.e("url", appendUrl);

            URL url = new URL(appendUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            String responseCode = connection.getResponseCode() + "";
            Log.e("code", responseCode);

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text = "", line;
            while ((line = rd.readLine()) != null)
            {
                text += line + "\n";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
