package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;

import com.github.mikephil.charting.charts.Chart;

import java.util.List;

public abstract class Graph {
    private List<GraphData> graphData;
    private Context context;

    public Graph(List<GraphData> graphData, Context context) {
        this.graphData = graphData;
        this.context = context;
    }

    public abstract void initEntries();

    public abstract void initChart();

    public List<GraphData> getGraphData() {
        return graphData;
    }

    public Context getContext() {
        return context;
    }

    public abstract Chart getChart();
}
