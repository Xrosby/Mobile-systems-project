package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.view.View;

import com.github.mikephil.charting.charts.Chart;

public interface Graph {

    public void initEntries();
    public void initChart();
    public Chart getChart();
}
