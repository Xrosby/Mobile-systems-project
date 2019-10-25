package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;
import android.util.Log;

import com.example.wallshaveears.database.entities.Traffic;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LineGraph extends Graph {

    private LineChart lineChart;
    private List<ILineDataSet> lineDataSets;
    private LineData lineData;
    private List<List<Entry>> lineEntriesList;

    public LineGraph(List<Traffic> graphData, Context context) {
        super(graphData, context);
        this.initChart();
    }

    @Override
    public void initEntries() {
        this.lineEntriesList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(1);
        List<String> appNames =
                this.getGraphData().stream()
                        .filter( distinctByKey(p -> p.getAppName()) )
                        .collect( Collectors.toList() ).stream().map(traffic -> {
                    return traffic.getAppName();
                }).collect(Collectors.toList());
        appNames.forEach(appName -> {
            List<Traffic> trafficData = this.getGraphData()
                    .stream()
                    .filter(traffic -> traffic.getAppName().equals(appName))
                    .collect(Collectors.toList());
            List<Entry> entries = new ArrayList<>();
            trafficData.forEach(traffic -> {
                entries.add(new Entry(count.getAndIncrement(), traffic.getTxBytes()));
            });
            lineEntriesList.add(entries);
            count.set(1);
        });
    }

    @Override
    public void initChart() {
        this.initEntries();
        this.lineChart = new LineChart(this.getContext());
        this.lineDataSets = new ArrayList<>();
        lineEntriesList.forEach(lineEntries -> {
            //TODO: ADD THE APP NAME AS LABEL AND PROVIDE DISTINCT COLOR FOR EACH DATASET
            lineDataSets.add(new LineDataSet(lineEntries, "Some label"));
        });
        GraphConfigurations.setLineChartConfigurations(lineChart, lineDataSets);
        lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }

    @Override
    public LineChart getChart() {
        return this.lineChart;
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
