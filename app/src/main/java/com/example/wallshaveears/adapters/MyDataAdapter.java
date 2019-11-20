package com.example.wallshaveears.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallshaveears.R;

import java.util.ArrayList;
import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder>
{
    private List<String> dataRows;

    public MyDataAdapter(ArrayList<String> dataRows)
    {
        ArrayList<String> input = new ArrayList<>();
        for (String s : dataRows)
        {
            if (s.contains("YouTube"))
            {
                input.add(s);
            }
        }
        this.dataRows = input;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_mydata, parent, false);
        TextView data = view.findViewById(R.id.myDataText);
        return new MyViewHolder(view, data);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.data.setText(dataRows.get(position));
    }

    @Override
    public int getItemCount()
    {
        return dataRows.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView data;

        public MyViewHolder(@NonNull View itemView, TextView data)
        {
            super(itemView);
            this.data = data;
        }
    }
}
