package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;

public class LineGraph extends Graph {

    public LineGraph(List<GraphData> graphData, Context context) {
        super(graphData, context);
    }

    @Override
    public void initEntries() {

    }

    @Override
    public void initChart() {

    }

    @Override
    public LineChart getChart() {
        return null;
    }
}
