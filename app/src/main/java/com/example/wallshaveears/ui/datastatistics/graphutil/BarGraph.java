package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BarGraph extends Graph {

    private HashMap<String, Long[]> appAndByteMap = new HashMap<>();
    private HorizontalBarChart barChart;
    private BarDataSet barDataSet;
    private BarData barData;
    private List<BarEntry> barEntries;

    public BarGraph(List<GraphData> graphData, Context context) {
        super(graphData, context);
        this.sumData();
        this.initChart();
    }

    @Override
    public void initEntries() {
        this.barEntries = new ArrayList<>();
        this.appAndByteMap.forEach((appName, bytes) -> {

            long recievedBytes = bytes[0];
            long transmittedBytes = bytes[1];

            Log.e("Adding entry: ", "" + "App: " + appName + "  - TX: " + transmittedBytes + " - RX: " + recievedBytes);

            BarEntry entry = new BarEntry(
                    0f, new float[]{recievedBytes, transmittedBytes});
            entry.setData(appName);

            barEntries.add(entry);
        });


    }




    @Override
    public void initChart() {
        this.initEntries();
        this.barChart = new HorizontalBarChart(this.getContext());
        barDataSet = new BarDataSet(barEntries, "Data flow");


        GraphConfigurations.setChartConfigurations(barChart, barDataSet);
        barChart.setFitBars(true);

        barData = new BarData(barDataSet);
        barChart.setData(barData);

        barDataSet.setStackLabels(new String[]{"Transmitted", "Recieved"});

        barChart.invalidate();

    }

    @Override
    public HorizontalBarChart getChart() {
        return this.barChart;

    }

    private void sumData() {
        final Map<String, Long> transmittedBytes =
                this.getGraphData()
                        .stream()
                        .collect(Collectors
                                .groupingBy(GraphData::getAppName, Collectors.summingLong(GraphData::getTransmittedBytes)));
        final Map<String, Long> recievedBytes =
                this.getGraphData()
                        .stream()
                        .collect(Collectors.groupingBy(GraphData::getAppName, Collectors.summingLong(GraphData::getReceivedBytes)));

        transmittedBytes.forEach((tk, tv) -> {
            recievedBytes.forEach((rk, rv) -> {
                if (tk.equals(rk)) {
                    this.appAndByteMap.put(tk, new Long[]{rv, tv});
                }
            });

        });

    }
}

