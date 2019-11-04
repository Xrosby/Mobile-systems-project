package com.example.wallshaveears.graphutil;

import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.Random;
import java.util.TimerTask;

public class AddEntry extends TimerTask {

    private Chart chart;
    private Random random = new Random();

    public AddEntry(Chart chart) {
        this.chart = chart;
    }
    @Override
    public void run() {
        if(chart instanceof HorizontalBarChart) {
            addBarEntry();
        } else if(chart instanceof PieChart) {
            addPieEntry();
        } else if(chart instanceof LineChart) {
            addLineEntry();
        }
    }

    private void addLineEntry() {
        //TODO: Update the chart to set x min and x max
        LineChart chart = (LineChart)this.chart;
        for(int i = 0; i < chart.getLineData().getDataSetCount(); i++) {
            addLineChartEntry(chart, i);
            setNewLineChartAxis(chart, i);
        }
        chart.notifyDataSetChanged();
        chart.invalidate();
    }

    private void addLineChartEntry(LineChart chart, int datasetIndex) {
        Entry entry = createNewLineEntry(chart, datasetIndex);
        chart.getLineData().addEntry(entry, datasetIndex);
    }

    private Entry createNewLineEntry(Chart chart, int datasetIndex) {
        LineChart lineChart = (LineChart)chart;
        int min = ((datasetIndex+1) * 3) * 10;
        int max = ((datasetIndex+1) * 3) * 11;
        long randomRx = (long)random.nextInt(max) + min;
        float count = lineChart.getXChartMax() + 0.2f;
        Entry entry = new Entry(count, randomRx);
        return entry;
    }


    private void setNewLineChartAxis(Chart chart, int datasetIndex) {
        LineChart lineChart = (LineChart)chart;
        XAxis xAxis = chart.getXAxis();
        ILineDataSet lineData = lineChart.getLineData().getDataSetByIndex(datasetIndex);
        float datasetMinimum = lineData.getXMin();
        float datasetMaximum = lineData.getXMax();
        lineData.removeFirst();
        xAxis.setAxisMinimum(datasetMinimum);
        xAxis.setAxisMaximum(datasetMaximum);
    }

    private void addBarEntry() {
        BarChart chart = (BarChart)this.chart;
    }

    private void addPieEntry() {
        PieChart chart = (PieChart)this.chart;
    }
}


