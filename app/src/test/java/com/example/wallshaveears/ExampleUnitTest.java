package com.example.wallshaveears;

import com.example.wallshaveears.ui.datastatistics.graphutil.DataSource;
import com.example.wallshaveears.ui.datastatistics.graphutil.GraphData;
import com.example.wallshaveears.ui.datastatistics.graphutil.PieGraph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*
    @Test
    public void firstTimeStampTest() {
        List<GraphData> graphData = new ArrayList<>();
        graphData.add(new GraphData(10, "Google", 233, 101, 1568363093));
        graphData.add(new GraphData(10, "Google", 431, 564, 1570178676));
        graphData.add(new GraphData(10, "Google", 33, 75, 1568363093));
        graphData.add(new GraphData(10, "Google", 82, 211, 1568363093));
        graphData.add(new GraphData(10, "Google", 319, 731, 1568363093));

        PieGraph pieGraph = new PieGraph(graphData, DataSource.RX, null);
        assertEquals(1568363093, pieGraph.firstTimeStamp("Google"));


    }

     */
}