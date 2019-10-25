package com.example.wallshaveears.ui.datastatistics.graphutil;

import android.content.Context;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * A PieGraph uses a list of GraphData
 * to generate MPAndroidChart PieEntry objects
 * and instantiating a MPAndroidChart PieChart
 * with the accumulated data specified by
 * the DataSource - either Recieved Data (RX) or
 * Transmitted Data (TX)
 */

public class PieGraph extends Graph {
    private Map<String, Long> appAndByteMap;

    private PieChart pieChart;
    private PieDataSet pieDataSet;
    private PieData pieData;
    private List<PieEntry> pieEntries;
    private DataSource dataSource;

    /**
     * @param graphData  is the list of data for display in the generated PieChart
     * @param dataSource defines whether the displayed data should be the apps
     *                   Recieved Data (RX) or Transmitted Data (TX)
     * @param context    is parsed to enable insert of the generated graph
     */
    public PieGraph(List<GraphData> graphData, DataSource dataSource, Context context) {
        super(graphData, context);
        this.dataSource = dataSource;
        this.appAndByteMap = new HashMap<String, Long>();
        initChart();
    }


    @Override
    public void initEntries() {
       this.sumData();
       this.pieEntries = new ArrayList<>();
       appAndByteMap.forEach((k, v) -> pieEntries.add(createEntry(k, v)));
    }

    private PieEntry createEntry(String appName, long bytes) {
        String label = this.createLabel(appName);
        return new PieEntry(bytes, label);
    }



    private long firstTimeStamp(String appName) {
        long firstTimeStamp = -1;
        try {
            firstTimeStamp = this.getGraphData()
                    .stream()
                    .filter(data -> data.getAppName() == appName)
                    .collect(Collectors.toList())
                    .stream()
                    .min(Comparator.comparing(GraphData::getTimeStamp))
                    .orElseThrow(NoSuchElementException::new).getTimeStamp();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return firstTimeStamp;
    }

    private String createLabel(String appName) {
        long timeStamp = this.firstTimeStamp(appName);
        String label = appName;
        return label;

    }

    /**
     * Chart initiation with the specified parameters
     * @valueTextSize() is set to fit with the application
     * and should not be altered with
     */
    @Override
    public void initChart() {

        this.initEntries();
        this.pieChart = new PieChart(this.getContext());
        pieDataSet = new PieDataSet(pieEntries, "Data Leaks");
        pieData = new PieData(pieDataSet);
        GraphConfigurations.setPieChartConfigurations(pieChart, pieDataSet);
        pieChart.getLegend().setTextColor(GraphConfigurations.labelColor);
        pieChart.setData(pieData);
        this.pieChart.invalidate();
    }

    /**
     * Depending on the DataSource specified
     * all RX or TX bytes will be summed up and linked
     * to the source (Name of the application)
     */
    private void sumData() {
        if(this.dataSource == DataSource.RX) {
            this.appAndByteMap = this.getGraphData()
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    GraphData::getAppName,
                                    Collectors.summingLong(
                                            GraphData::getReceivedBytes)));
        }else {
            this.appAndByteMap = this.getGraphData()
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    GraphData::getAppName,
                                    Collectors.summingLong(
                                            GraphData::getTransmittedBytes)));
        }
    }

    /**
     * @return the finished and loaded PieGraph
     * ready for insert into an activity
     */
    @Override
    public PieChart getChart() {
        return this.pieChart;
    }


}
