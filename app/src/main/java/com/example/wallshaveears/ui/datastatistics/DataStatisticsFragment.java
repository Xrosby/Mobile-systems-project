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
        final TextView textView = root.findViewById(R.id.txt_graph_loading);

        dataStatisticsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        this.initCharts(root);
        textView.setVisibility(View.GONE);

        return root;
    }

    private void initCharts(View root) {
        this.graphBuilder = new GraphBuilder(root.getContext());
        this.initPieChart(root);
        this.initBarChart(root);
    }

    private void initPieChart(View root) {
        List<GraphData> graphData = new ArrayList<>();
        graphData.add(new GraphData("Google", 132333, 7501));
        graphData.add(new GraphData("Facebook", 90133, 1228));
        graphData.add(new GraphData("Lasses skumle app", 141432434, 12));
        PieChart pieChart = (PieChart) this.graphBuilder.createGraph(GraphType.PIE, graphData);
        RelativeLayout pieChartContainer = (RelativeLayout) root.findViewById(R.id.pie_chart_container);
        pieChartContainer.addView(pieChart);

    }
    private void initBarChart(View root) {
        System.out.println("Bar chart not supported yet");
    }
}



