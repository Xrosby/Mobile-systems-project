package com.example.wallshaveears.ui.datastatistics.graphutil.conf;

import android.graphics.Color;

public class BarConfigs {
    //Bar chart configurations
    public final static int recievedByteBarColor = Color.rgb(228, 235, 141);
    public final static int transmittedByteBarColor = Color.rgb(167, 174, 219);
    public final static int[] barColorTemplate = new int[]{recievedByteBarColor, transmittedByteBarColor};
    public final static boolean fitBars = true;
    public final static String legendDescription= "Recieved And Transmitted Bytes in KB";
}
