package com.example.wallshaveears.graphutil.graphs;

import android.content.Context;

import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.graphutil.RXTXWrapper;
import com.example.wallshaveears.graphutil.conf.GraphConfigurations;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BarGraph extends Graph {

    private TreeMap<String, RXTXWrapper> appAndByteMap = new TreeMap<>();
    private HorizontalBarChart barChart;
    private BarDataSet barDataSet;
    private BarData barData;
    private List<BarEntry> barEntries;

    public BarGraph(List<Traffic> graphData, Context context) {
        super(graphData, context);
        this.prepareData();
        this.initChart();
    }

    @Override
    public void initEntries() {
        this.barEntries = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(1);
        this.appAndByteMap.forEach((appName, bytes) -> {
            long recievedBytes = bytes.getRecievedBytes();
            long transmittedBytes = bytes.getTransmittedBytes();
            BarEntry entry = new BarEntry(
                    count.getAndIncrement(), new float[]{recievedBytes, transmittedBytes});
            barEntries.add(entry);
        });
    }

    @Override
    public void initChart() {
        this.initEntries();
        this.barChart = new HorizontalBarChart(this.getContext());
        barDataSet = new BarDataSet(barEntries, "Data flow");
        GraphConfigurations.setBarChartConfigurations(barChart, barDataSet);
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        this.formatGraphAxis(barChart.getXAxis(), new YAxis[]{barChart.getAxisLeft(), barChart.getAxisRight()});
        barDataSet.setStackLabels(new String[]{"Transmitted", "Recieved"});
        barChart.invalidate();
    }


    private void formatGraphAxis(XAxis xAxis, YAxis[] yAxis) {
        //TODO: This should ideally be part of barchart configurations
        this.formatYAxis(yAxis);
        this.formatXAxis(xAxis);
    }


    private void formatXAxis(XAxis xAxis) {
        //TODO: This should ideally be part of barchart configurations
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(30);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        this.setAppNameLabels(xAxis);
    }

    private void formatYAxis(YAxis[] yAxis) {
        //TODO: This should ideally be part of barchart configurations
        Arrays.stream(yAxis).forEach(y ->{
            y.setValueFormatter(new IndexAxisValueFormatter(){
              @Override
              public String getFormattedValue(float value) {
                  return value + "kb";
              }
            });
            y.setDrawGridLines(false);
            y.setDrawAxisLine(false);
            y.setDrawLabels(false);
        });
    }

    private void setAppNameLabels(XAxis xAxis) {
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            ArrayList<String> labels =  new ArrayList<>(appAndByteMap.keySet());
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int)value -1);
            }
        });
    }

    private void prepareData() {
        this.sumData();
        this.sortData();
    }

    private Map<String, Long> sumTransmittedBytes() {
        Map<String, Long> transmittedBytes = this.getGraphData()
                .stream()
                .collect(Collectors
                        .groupingBy(Traffic::getAppName, Collectors.summingLong(Traffic::getTxAccumulate)));
        return transmittedBytes;
    }

    private Map<String, Long> sumRecievedBytes() {
        Map<String, Long> recievedBytes = this.getGraphData()
                .stream()
                .collect(Collectors.groupingBy(Traffic::getAppName, Collectors.summingLong(Traffic::getRxAccumulate)));
        return recievedBytes;
    }

    private void sumData() {
        final Map<String, Long> transmittedBytes = sumTransmittedBytes();
        final Map<String, Long> recievedBytes = sumRecievedBytes();
        transmittedBytes.forEach((tk, tv) -> {
            recievedBytes.forEach((rk, rv) -> {
                if (tk.equals(rk)) {
                    String appName = tk;
                    this.appAndByteMap.put(appName, new RXTXWrapper(rv, tv));
                }
            });

        });
    }

    private void sortData() {
        Comparator<String> comparator = new AppDataComparator(appAndByteMap);
        TreeMap<String, RXTXWrapper> result = new TreeMap<String, RXTXWrapper>(comparator);
        result.putAll(appAndByteMap);
        this.appAndByteMap = result;
    }

    @Override
    public HorizontalBarChart getChart() {
        return this.barChart;

    }

}

