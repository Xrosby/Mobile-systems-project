package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class GraphConfigurations {

    //Bar chart configurations
    public final static int recievedByteBarColor = Color.rgb(252, 186, 3);
    public final static int transmittedByteBarColor = Color.rgb(38, 120, 171);
    public final static int[] barColorTemplate = new int[] {recievedByteBarColor, transmittedByteBarColor};

    //Pie chart configurations
    public final static int[] pieColorTemplate = ColorTemplate.VORDIPLOM_COLORS;

    //Shared chart confingurations
    public final static int graphColor = Color.BLACK;
    public final static float graphValueTextSize = 10f;
    public final static int labelColor = Color.BLACK;
    public final static RelativeLayout.LayoutParams layoutParams =new RelativeLayout.
            LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT);



    public static void setBarChartConfigurations(Chart chart, DataSet dataSet) {

        dataSet.setValueTextColor(GraphConfigurations.graphColor);
        dataSet.setValueTextSize(GraphConfigurations.graphValueTextSize);
        dataSet.setColors(barColorTemplate);
        BarChart barChart = (BarChart) chart;
        barChart.setFitBars(true);
        barChart.setLayoutParams(GraphConfigurations.layoutParams);

    }

    public static void setPieChartConfigurations(Chart chart, DataSet dataSet) {
        dataSet.setValueTextColor(GraphConfigurations.graphColor);
        dataSet.setValueTextSize(GraphConfigurations.graphValueTextSize);
        dataSet.setColors(pieColorTemplate);
        chart.setLayoutParams(GraphConfigurations.layoutParams);

    }

}
