package com.example.wallshaveears.ui.permissions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PermissionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PermissionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is permissions fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}