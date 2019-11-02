package com.example.wallshaveears.graphutil;

import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
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
    private GraphType graphType;
    private Random random = new Random();

    public AddEntry(Chart chart, GraphType graphType) {
        this.chart = chart;
        this.graphType = graphType;
    }

    @Override
    public void run() {
        if(graphType == GraphType.BAR) {
            addBarEntry();
        } else if(graphType == GraphType.PIE) {
            addPieEntry();
        } else if(graphType == GraphType.LINE) {
            addLineEntry();
        }
    }

    private void addLineEntry() {
        //TODO: Update the chart to set x min and x max
        LineChart chart = (LineChart)this.chart;
        for(int i = 0; i < chart.getLineData().getDataSetCount(); i++) {
            addLineChartEntry(chart, i);
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
        int count = lineChart.getLineData().getDataSetByIndex(datasetIndex).getEntryCount();
        Entry entry = new Entry(count, randomRx);
        return entry;
    }


    private void setNewLineChartAxis(Chart chart, int datasetIndex) {
        LineChart lineChart = (LineChart)chart;
        XAxis xAxis = chart.getXAxis();
        ILineDataSet lineData = lineChart.getLineData().getDataSetByIndex(datasetIndex);
        float datasetMinimum = lineData.getXMin();
        float datasetMaximum = lineData.getXMax();
        //lineData.removeFirst();
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


