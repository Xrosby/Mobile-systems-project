package com.example.wallshaveears.ui.datastatistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.wallshaveears.R;
import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphBuilder;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphType;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DataStatisticsFragment extends Fragment {

    private DataStatisticsViewModel dataStatisticsViewModel;
    private GraphBuilder graphBuilder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataStatisticsViewModel =
                ViewModelProviders.of(this).get(DataStatisticsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_datastatistics, container, false);

        this.initCharts(root);

        return root;
    }

    private void initCharts(View root) {
        this.graphBuilder = new GraphBuilder(root.getContext());
        this.initBarChart(root);
        this.initLineChart(root);
    }

    private void initPieChart(View root) {
        throw new UnsupportedOperationException("Pie chart is not in use");


    }

    private void initLineChart(View root) {
        List<Traffic> graphData = this.getDummyData();

        LineChart lineChart = (LineChart) this.graphBuilder.createGraph(GraphType.LINE, graphData);
        RelativeLayout lineChartContainer  = (RelativeLayout) root.findViewById(R.id.line_chart_container);
        if(lineChart != null) {
            lineChartContainer.addView(lineChart);
        }
    }


    private void initBarChart(View root) {
        List<Traffic> graphData = this.getDummyData();

        HorizontalBarChart barChart = (HorizontalBarChart) this.graphBuilder.createGraph(GraphType.BAR, graphData);
        RelativeLayout barChartContainer = (RelativeLayout) root.findViewById(R.id.bar_chart_container);
        if(barChart != null) {
            barChartContainer.addView(barChart);
        }
    }

    private List<Traffic> getDummyData() {

        // _______________________ DUMMY DATA FOR TESTING BARCHART __________________________________________________________________//
        List<Traffic> graphData = new ArrayList<>();


        Date date = new Date(1570781692);
        long timeIncrement = 100000;


        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5));

        date.setTime(1570781692);

        graphData.add(new Traffic("Lasses Shady App", date, 735, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Lasses Shady App", date, 321, 23, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Lasses Shady App", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Lasses Shady App", date, 125, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Lasses Shady App", date, 73, 233, 101, 5, 5));

        date.setTime(1570781692);

        graphData.add(new Traffic("Baloo", date, 525, 1233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Baloo", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Baloo", date, 25, 233, 1101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Baloo", date, 525, 1233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("Baloo", date, 525, 233, 101, 5, 5));

        date.setTime(1570781692);

        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 1101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("RobertAnalytics", date, 5215, 1133, 1301, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5));
        date.setTime(date.getTime() + timeIncrement);
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5));


        return graphData;
    }
}



