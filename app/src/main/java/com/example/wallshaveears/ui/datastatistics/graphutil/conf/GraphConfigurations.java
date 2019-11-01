package com.example.wallshaveears.ui.datastatistics.graphutil.conf;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.Random;

public class GraphConfigurations {

    // ____________________ BAR CHART CONFIGURATIONS ____________________//

    public static void setBarChartConfigurations(Chart chart, DataSet dataSet) {
        setSharedConfigurations(dataSet, chart);
        configureBarDataSet(dataSet);
        configureBarChart(chart);
    }
    private static void configureBarDataSet(DataSet dataSet) {
        dataSet.setColors(BarConfigs.barColorTemplate);
    }
    private static void configureBarChart(Chart chart) {
        BarChart barChart = (BarChart) chart;
        barChart.setFitBars(BarConfigs.fitBars);
        Description des = new Description();
        des.setText(BarConfigs.legendDescription);
        barChart.setDescription(des);
    }




    // ______________ PIE CHART CONFIGURATIONS ____________________//

    public static void setPieChartConfigurations(Chart chart, DataSet dataSet) {
        setSharedConfigurations(dataSet, chart);
        configurePieDataSet(dataSet);
        configurePieChart(chart);
    }
    private static void configurePieDataSet(DataSet dataSet){
        dataSet.setColors(PieConfigs.pieColorTemplate);
    }

    private static void configurePieChart(Chart chart) {
        chart.getLegend().setTextColor(PieConfigs.labelColor);
    }

    //__________ LINE CHART CONFIGURATIONS __________//


    public static void setLineChartConfigurations(Chart chart, LineDataSet dataSet) {
        setSharedConfigurations(dataSet, chart);
        configureLineDataSet(dataSet);
        configureLineChart(chart);
    }

    private static void configureLineChart(Chart chart) {
        LineChart lineChart = (LineChart) chart;
        lineChart.getLegend().setWordWrapEnabled(true);
        Description des = new Description();
        des.setText(LineConfigs.legendDescription);
        lineChart.setDescription(des);
    }
    private static void configureLineDataSet(DataSet dataSet) {
        LineDataSet lineDataSet = (LineDataSet) dataSet;
        lineDataSet.setDrawCircles(LineConfigs.enablePoints);
        lineDataSet.setColor(generateRandomColor(), 100);
    }

    private static void setSharedConfigurations(DataSet dataSet, Chart chart) {
        dataSet.setValueTextColor(SharedChartConfigs.graphColor);
        dataSet.setValueTextSize(SharedChartConfigs.graphValueTextSize);
        chart.setLayoutParams(SharedChartConfigs.layoutParams);
    }
    private static int generateRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(100);
        int b = rand.nextInt(255);
        return Color.rgb(r, g, b);
    }

}
