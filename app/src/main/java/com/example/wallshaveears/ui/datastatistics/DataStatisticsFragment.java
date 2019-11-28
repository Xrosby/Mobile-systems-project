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
import com.example.wallshaveears.database.TrafficRepository;
import com.example.wallshaveears.database.entities.Traffic;
import com.example.wallshaveears.graphutil.AddEntry;
import com.example.wallshaveears.graphutil.tests.DummyDataGenerator;
import com.example.wallshaveears.graphutil.GraphBuilder;
import com.example.wallshaveears.graphutil.GraphType;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;
import java.util.Timer;

public class DataStatisticsFragment extends Fragment {

    private DataStatisticsViewModel dataStatisticsViewModel;
    private GraphBuilder graphBuilder;
    private TrafficRepository trafficRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataStatisticsViewModel =
                ViewModelProviders.of(this).get(DataStatisticsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_datastatistics, container, false);

        this.trafficRepository = new TrafficRepository(root.getContext());
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

        //List<Traffic> graphData = trafficRepository.getAllTraffic();

        LineChart lineChart = (LineChart) this.graphBuilder.createGraph(GraphType.LINE, graphData);
        RelativeLayout lineChartContainer  = (RelativeLayout) root.findViewById(R.id.line_chart_container);
        if(lineChart != null) {
            lineChartContainer.addView(lineChart);
        }

        Timer timer = new Timer();
        timer.schedule(new AddEntry(lineChart), 0, 1000);

    }




    private void initBarChart(View root) {
       // List<Traffic> graphData = this.getDummyData();
        List<Traffic> graphData = this.trafficRepository.getAllTraffic();

        HorizontalBarChart barChart = (HorizontalBarChart) this.graphBuilder.createGraph(GraphType.BAR, graphData);
        RelativeLayout barChartContainer = (RelativeLayout) root.findViewById(R.id.bar_chart_container);
        if(barChart != null) {
            barChartContainer.addView(barChart);
        }
    }

    private List<Traffic> getDummyData() {
        DummyDataGenerator ddg = new DummyDataGenerator();
        List<Traffic> graphData = ddg.generateDummyData(100);
        return graphData;
    }
}



