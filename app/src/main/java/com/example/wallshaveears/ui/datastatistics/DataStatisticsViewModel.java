package com.example.wallshaveears.ui.datastatistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.charts.PieChart;

public class DataStatisticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private PieChart pieChart;

    public DataStatisticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Loading graphs..");
    }

    public LiveData<String> getText() {
        return mText;
    }
}