package com.example.wallshaveears.graphutil.graphs;

import com.example.wallshaveears.database.entities.Traffic;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class ByteValueFormatter extends ValueFormatter
{
    @Override
    public String getFormattedValue(float value)
    {
        return Traffic.humanReadableByteCount((long) value, true);
    }
}
