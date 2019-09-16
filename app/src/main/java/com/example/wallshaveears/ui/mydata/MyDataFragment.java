package com.example.wallshaveears.ui.mydata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wallshaveears.R;

public class MyDataFragment extends Fragment {

    private MyDataViewModel myDataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myDataViewModel =
                ViewModelProviders.of(this).get(MyDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mydata, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        myDataViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}