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
        // this.initBarChart(root);
    }

    private void initPieChart(View root) {
        throw new UnsupportedOperationException("Pie chart is not in use");


    }

    private void initBarChart(View root) {
        // _______________________ DUMMY DATA FOR TESTING BARCHART __________________________________________________________________//
        List<Traffic> graphData = new ArrayList<>();


        //Date date = new Date(1570781692);
        long date = 1570781692;

        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("FlappyBird", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));


        graphData.add(new Traffic("Lasses Shady App", date, 5235, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Lasses Shady App", date, 525, 23, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Lasses Shady App", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Lasses Shady App", date, 5325, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Lasses Shady App", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));


        graphData.add(new Traffic("Baloo", date, 525, 1233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Baloo", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Baloo", date, 25, 233, 1101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Baloo", date, 525, 1233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("Baloo", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));


        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 1101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("RobertAnalytics", date, 5215, 1133, 1301, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        graphData.add(new Traffic("RobertAnalytics", date, 525, 233, 101, 5, 5, 5, 101, 5, 1));
        /*
        graphData.add(new GraphData(10, "FlappyBird", 23, 901, 1570781992));
        graphData.add(new GraphData(10, "FlappyBird", 411, 11, 1570782292));
        graphData.add(new GraphData(10, "FlappyBird", 612, 21, 1570782592));
        graphData.add(new GraphData(10, "FlappyBird", 78, 981, 1570782892));



        graphData.add(new GraphData(10, "Yahoo", 112, 533, 1570781692));
        graphData.add(new GraphData(10, "Yahoo", 141, 23, 1570781992));
        graphData.add(new GraphData(10, "Yahoo", 33, 51, 1570782292));
        graphData.add(new GraphData(10, "Yahoo", 82, 61, 1570782592));
        graphData.add(new GraphData(10, "Yahoo", 12, 67, 1570782892));


         graphData.add(new GraphData(30, "Lasses ApS", 434, 1122, 1570781692));
        graphData.add(new GraphData(30, "Lasses ApS", 434, 122, 1570781992));
        graphData.add(new GraphData(30, "Lasses ApS", 434, 122, 1570782292));
        graphData.add(new GraphData(30, "Lasses ApS", 434, 122, 1570782592));
        graphData.add(new GraphData(30, "Lasses ApS", 434, 122, 1570782892));



        graphData.add(new GraphData(10, "Baloo", 1321, 2033, 1570781692));
        graphData.add(new GraphData(10, "Baloo", 431, 23, 1570781992));
        graphData.add(new GraphData(10, "Baloo", 341, 51, 1570782292));
        graphData.add(new GraphData(10, "Baloo", 1234, 61, 1570782592));
        graphData.add(new GraphData(10, "Baloo", 122, 67, 1570782892));

        graphData.add(new GraphData(10, "Gringos", 112, 1515, 1570781692));
        graphData.add(new GraphData(10, "Gringos", 111, 1351, 1570781992));
        graphData.add(new GraphData(10, "Gringos", 51, 3154, 1570782292));
        graphData.add(new GraphData(10, "Gringos", 115, 1111, 1570782592));
        graphData.add(new GraphData(10, "Gringos", 51, 155, 1570782892));


        graphData.add(new GraphData(10, "Lasses Shady App", 292, 1133, 1570781692));
        graphData.add(new GraphData(10, "Lasses Shady App", 451, 155, 1570781992));
        graphData.add(new GraphData(10, "Lasses Shady App", 133, 166, 1570782292));
        graphData.add(new GraphData(10, "Lasses Shady App", 812, 100, 1570782592));
        graphData.add(new GraphData(10, "Lasses Shady App", 512, 11, 1570782892));

        graphData.add(new GraphData(10, "RobertAnalyics", 299, 514, 1570781692));
        graphData.add(new GraphData(10, "RobertAnalyics", 866, 615, 1570781992));
        graphData.add(new GraphData(10, "RobertAnalyics", 733, 99, 1570782292));
        graphData.add(new GraphData(10, "RobertAnalyics", 988, 1023, 1570782592));
        graphData.add(new GraphData(10, "RobertAnalyics", 72, 511, 1570782892));


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

         */






        //________________________________________________________________________________________________________________________________//

        HorizontalBarChart barChart = (HorizontalBarChart) this.graphBuilder.createGraph(GraphType.BAR, graphData);
        RelativeLayout barChartContainer = (RelativeLayout) root.findViewById(R.id.bar_chart_container);
        barChartContainer.addView(barChart);
    }
}



