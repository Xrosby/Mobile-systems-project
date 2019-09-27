package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;
import java.util.stream.Collectors;


public class PieGraph implements Graph {

    private List<GraphData> graphData;

    private PieChart pieChart;
    private PieDataSet pieDataSet;
    private PieData pieData;
    private List<PieEntry> pieEntries;

    private Context context;

    public PieGraph(List<GraphData> graphData, Context context) {
        this.context = context;
        this.graphData = graphData;
        initChart();
    }

    @Override
    public void initEntries() {

        if(pieEntries != null) {
            Log.e("ENTRIES BEFORE: ", "" + this.pieEntries.size());
        }

        this.pieEntries = this.graphData
                .stream()
                .map(data -> {
                    return createEntry(data);
                }).collect(Collectors.toList());

        Log.e("ENTRIES AFTER: ", "" + this.pieEntries.size());

    }




    public PieEntry createEntry(GraphData graphData) {

        Log.e("GraphDATA: ", "" + graphData.getAppName() + " : " + graphData.getTransmittedBytes());


        return new PieEntry(graphData.getTransmittedBytes(), graphData.getAppName());
    }

    @Override
    public void initChart() {
        this.initEntries();


        this.pieChart = new PieChart(this.context);

        pieDataSet = new PieDataSet(pieEntries, "Data Leaks");
        pieData = new PieData(pieDataSet);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        Log.e("ENTRIES BEFORE SETTING DATA: ", "" + this.pieEntries.size());

        pieChart.setData(pieData);

    }

    @Override
    public PieChart getChart() {
        this.pieChart.invalidate();
        return this.pieChart;
    }


    /*
     //_______ PIE Chart initialization _________ //

    private void initPieChart() {
        initiatePieDummyData();

        pieDataSet = new PieDataSet(pieEntries, "Data Leak and The Monetizers");
        pieData = new PieData(pieDataSet);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initiatePieDummyData() {
        this.pieEntries = new ArrayList<>();
        String[] buttholeCompanies = {"Google Admob", "Lytica", "AdColony", "IronSource", "FanBytes", "LeadBolt"};

        pieEntries = new ArrayList<>();
        for (int i = 0; i < buttholeCompanies.length; i++) {
            int randomDataAmount = (int) (Math.random() * 500 + 1);
            pieEntries.add(new PieEntry(randomDataAmount, buttholeCompanies[i]));
        }
    }





     */
}
