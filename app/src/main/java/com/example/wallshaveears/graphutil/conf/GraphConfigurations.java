package com.example.wallshaveears.graphutil.conf;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineDataSet;

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
        lineChart.setBackgroundColor(LineConfigs.backgroundColor);
        lineChart.setBorderColor(LineConfigs.borderColor);
        lineChart.setBorderWidth(LineConfigs.borderWidth);
        lineChart.getLegend().setWordWrapEnabled(true);
        setLineChartDescription(lineChart);
    }

    private static void setLineChartDescription(LineChart chart) {
        Description des = new Description();
        des.setText(LineConfigs.legendDescription);
        chart.setDescription(des);
    }

    private static void configureLineDataSet(DataSet dataSet) {
        LineDataSet lineDataSet = (LineDataSet) dataSet;
        lineDataSet.setDrawCircles(LineConfigs.enablePoints);
        lineDataSet.setColor(generateRandomColor(LineConfigs.rgbBounds), 100);
    }


    //_________ SHARED CHART CONFIGURATIONS __________ //

    private static void setSharedConfigurations(DataSet dataSet, Chart chart) {
        dataSet.setValueTextColor(SharedChartConfigs.graphColor);
        dataSet.setValueTextSize(SharedChartConfigs.graphValueTextSize);
        chart.setLayoutParams(SharedChartConfigs.layoutParams);
    }
    private static int generateRandomColor(int[] rgbBounds) {
        Random rand = new Random();
        int r = rand.nextInt(rgbBounds[0]);
        int g = rand.nextInt(rgbBounds[1]);
        int b = rand.nextInt(rgbBounds[2]);
        return Color.rgb(r, g, b);
    }

}
