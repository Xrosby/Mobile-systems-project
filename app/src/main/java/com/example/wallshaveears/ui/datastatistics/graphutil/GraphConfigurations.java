package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class GraphConfigurations {

    public final static int graphColor = Color.BLACK;
    public final static int[] colorTemplate = ColorTemplate.MATERIAL_COLORS;
    public final static float graphValueTextSize = 20f;
    public final static int labelColor = Color.BLACK;
    public final static RelativeLayout.LayoutParams layoutParams =new RelativeLayout.
            LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT);

    public static void setChartConfigurations(Chart chart, DataSet dataSet) {
        dataSet.setValueTextColor(GraphConfigurations.graphColor);
        dataSet.setValueTextSize(GraphConfigurations.graphValueTextSize);
        dataSet.setColors(GraphConfigurations.colorTemplate);
        chart.setLayoutParams(GraphConfigurations.layoutParams);

    }

}
