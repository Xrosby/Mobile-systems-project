package com.example.wallshaveears.ui.mydata;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallshaveears.R;
import com.example.wallshaveears.adapters.MyDataAdapter;
import com.example.wallshaveears.database.TrafficRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDataFragment extends Fragment
{
    private RecyclerView recyclerView;
    private MyDataAdapter adapter;

    //private MyDataViewModel myDataViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_mydata, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        TrafficRepository repo = new TrafficRepository(this.getContext());
        ArrayList<String> data = new ArrayList<>(repo.getAllForTest());
        Log.e("Number of rows", data.size() + "");

        recyclerView = view.findViewById(R.id.mydata_list);
        adapter = new MyDataAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.notifyDataSetChanged();


    }
}