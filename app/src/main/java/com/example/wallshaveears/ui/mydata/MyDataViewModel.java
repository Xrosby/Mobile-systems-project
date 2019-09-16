package com.example.wallshaveears.ui.mydata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is \"My Data\" fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}