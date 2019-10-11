package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

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
        Log.e("APPDATA", this.appAndByteMap.toString());
        this.appAndByteMap.forEach((appName, bytes) -> {
            long recievedBytes = bytes[0];
            long transmittedBytes = bytes[1];
            barEntries.add(new BarEntry(
                    recievedBytes,
                    transmittedBytes,
                    appName));
        });
    }

    @Override
    public void initChart() {
        this.initEntries();
        this.barChart = new HorizontalBarChart(this.getContext());

        this.barEntries.forEach(entry ->  {
           Log.e("ENTRY: ", entry.toString());
        });

        barDataSet = new BarDataSet(barEntries, "Data flow");
        GraphConfigurations.setChartConfigurations(barChart, barDataSet);
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();

    }

    @Override
    public BarChart getChart() {
        return this.barChart;

    }

    private void sumData() {
        final Map<String, Long> transmittedBytes =
                this.getGraphData()
                        .stream()
                        .collect(Collectors
                                .toMap(GraphData::getAppName, GraphData::getTransmittedBytes, (name, bytes) -> bytes));
        final Map<String, Long> recievedBytes =
                this.getGraphData()
                        .stream()
                        .collect(Collectors.toMap(GraphData::getAppName, GraphData::getReceivedBytes, (name, bytes) -> bytes));


        Log.e("TRANSMITTED: ", transmittedBytes.toString());
        Log.e("RECIEVED: ", recievedBytes.toString());


        transmittedBytes.forEach((tk, tv) -> {
            recievedBytes.forEach((rk, rv) -> {
                if (tk.equals(rk)) {
                    this.appAndByteMap.put(tk, new Long[]{rv, tv});
                }
            });

        });
        /*

        for (Map.Entry<String, Long> transmitted : transmittedBytes.entrySet()) {


            for (Map.Entry<String, Long> recieved : recievedBytes.entrySet()) {


                if (transmitted.getKey().equals(recieved.getKey())) {
                    this.appAndByteMap.put(recieved.getKey(), new Long[]{recieved.getValue(), transmitted.getValue()});
                }


            }

        }
        */

    }
}

