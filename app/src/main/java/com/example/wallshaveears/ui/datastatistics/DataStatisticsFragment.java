package com.example.wallshaveears.ui.datastatistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wallshaveears.R;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphBuilder;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphData;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphType;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
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
        // this.initBarChart(root);
    }

    private void initPieChart(View root) {
        throw new UnsupportedOperationException("Pie chart is not in use");


    }

    private void initBarChart(View root) {
        // _______________________ DUMMY DATA FOR TESTING BARCHART __________________________________________________________________//
        List<GraphData> graphData = new ArrayList<>();

        graphData.add(new GraphData(10, "Google", 233, 101, 1570781692));
        graphData.add(new GraphData(10, "Google", 431, 564, 1570781992));
        graphData.add(new GraphData(10, "Google", 33, 75, 1570782292));
        graphData.add(new GraphData(10, "Google", 82, 211, 1570782592));
        graphData.add(new GraphData(10, "Google", 319, 731, 1570782892));


        graphData.add(new GraphData(20, "Facebook", 133, 228, 1570781692));
        graphData.add(new GraphData(20, "Facebook", 133, 228, 1570781992));
        graphData.add(new GraphData(20, "Facebook", 133, 228, 1570782292));
        graphData.add(new GraphData(20, "Facebook", 133, 228, 1570782592));
        graphData.add(new GraphData(20, "Facebook", 133, 228, 1570782892));


        graphData.add(new GraphData(30, "Lasses skumle app", 434, 122, 1570781692));
        graphData.add(new GraphData(30, "Lasses skumle app", 434, 122, 1570781992));
        graphData.add(new GraphData(30, "Lasses skumle app", 434, 122, 1570782292));
        graphData.add(new GraphData(30, "Lasses skumle app", 434, 122, 1570782592));
        graphData.add(new GraphData(30, "Lasses skumle app", 434, 122, 1570782892));


        //________________________________________________________________________________________________________________________________//

        HorizontalBarChart barChart = (HorizontalBarChart) this.graphBuilder.createGraph(GraphType.BAR, graphData);
        RelativeLayout barChartContainer = (RelativeLayout) root.findViewById(R.id.bar_chart_container);
        barChartContainer.addView(barChart);
    }
}



