package com.example.wallshaveears.ui.permissions;

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

public class PermissionsFragment extends Fragment {

    private PermissionsViewModel permissionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        permissionsViewModel =
                ViewModelProviders.of(this).get(PermissionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_permissions, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        permissionsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}