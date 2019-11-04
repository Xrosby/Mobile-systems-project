package com.example.wallshaveears.graphutil.conf;

import android.graphics.Color;

public class BarConfigs {
    //Bar chart configurations
    public final static int recievedByteBarColor = Color.rgb(255, 196, 0);
    public final static int transmittedByteBarColor = Color.rgb(0, 157, 255);
    public final static int[] barColorTemplate = new int[]{recievedByteBarColor, transmittedByteBarColor};
    public final static boolean fitBars = true;
    public final static String legendDescription= "Recieved And Transmitted Bytes in KB";
}
