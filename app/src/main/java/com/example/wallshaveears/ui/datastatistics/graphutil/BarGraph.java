package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class BarGraph implements Graph{

    private List<GraphData> graphData;


    private BarChart barChart;
    private BarDataSet barDataSet;
    private BarData barData;
    private List<BarEntry> barEntries;

    private Context context;

    public BarGraph(List<GraphData> graphData, Context context) {
    this.graphData = graphData;
    this.initChart();
    }

    @Override
    public void initEntries() {

    }

    @Override
    public void initChart() {
        this.initEntries();

    }

    @Override
    public Chart getChart() {
        throw new UnsupportedOperationException("Bar graphs are not implemented yet");
    }

    /*
     //________ BAR Chart Initialization ___________ //

    private void initBarChart() {
        initiateBarDummyData();

        barDataSet = new BarDataSet(barEntries, "MB data leak pr day");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();

    }

    private void initiateBarDummyData() {
        this.barEntries = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            int randomDataAmount = (int) (Math.random() * 25 + 1);
            barEntries.add(new BarEntry(i, randomDataAmount));
        }


    }
     */
}
