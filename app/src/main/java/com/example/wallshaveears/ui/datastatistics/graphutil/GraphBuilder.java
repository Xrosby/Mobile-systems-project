package com.example.wallshaveears.ui.datastatistics.graphutil;
import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.List;

public  class GraphBuilder {

    private Context context;

    public GraphBuilder(Context context) {
        this.context = context;
    }
    public Chart createGraph(GraphType graphType, List<GraphData> graphData) {
        Chart chart;
        switch (graphType) {
            case PIE:
                chart = createPieChart(graphData);
                break;
            case BAR:
                chart = createBarChart(graphData);
                break;
            default:
                chart = null;
        }
        return chart;
    }

    private Chart createPieChart(List<GraphData> graphData) {
        PieGraph pieGraph = new PieGraph(graphData, DataSource.TX, this.context);
        PieChart pieChart = pieGraph.getChart();
        return pieChart;
    }

    private Chart createBarChart(List<GraphData> graphData) {
        BarGraph barGraph = new BarGraph(graphData, this.context);
        BarChart barChart = barGraph.getChart();
        return barChart;
    }

    private Chart createLineChart(List<GraphData> graphData) {
        LineGraph lineGraph = new LineGraph(graphData, this.context);
        LineChart lineChart = lineGraph.getChart();
        return lineChart;
    }





}
