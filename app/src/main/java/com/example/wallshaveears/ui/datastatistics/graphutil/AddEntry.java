package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.util.Log;

import com.example.wallshaveears.ui.datastatistics.graphutil.GraphType;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;

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
        String[] appNames = new String[]{"Lasses Shady App", "Robert Analytics", "Google", "Facebook", "Spotify"};
        LineChart chart = (LineChart)this.chart;
        for(int i = 0; i < chart.getLineData().getDataSetCount(); i++) {
            int min = ((i+1) * 3) * 10;
            int max = ((i+1) * 3) * 11;
            long randomRx = (long)random.nextInt(max) + min;
            //chart.getLineData().getDataSetByIndex(i).removeFirst();
            Entry entry = new Entry(chart.getLineData().getDataSetByIndex(i).getEntryCount(), randomRx);
            chart.getLineData().addEntry(entry, i);
            chart.setVisibleXRange(chart.getLineData().getXMin(), chart.getLineData().getXMax());
        }
        chart.notifyDataSetChanged(); // let the chart know it's data changed
        chart.invalidate(); // refresh
    }

    private void addBarEntry() {
        BarChart chart = (BarChart)this.chart;
    }

    private void addPieEntry() {
        PieChart chart = (PieChart)this.chart;
    }
}


