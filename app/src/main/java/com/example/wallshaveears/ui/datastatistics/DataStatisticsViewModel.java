package com.example.wallshaveears.ui.datastatistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataStatisticsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DataStatisticsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is statistics fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}