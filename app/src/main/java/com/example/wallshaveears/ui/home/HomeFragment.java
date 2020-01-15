package com.example.wallshaveears.ui.home;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wallshaveears.MainActivity;
import com.example.wallshaveears.R;
import com.example.wallshaveears.network.ChargingReceiver;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button jobButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        jobButton = root.findViewById(R.id.jobButton);
        jobButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                stopJobScheduler(HomeFragment.this.getContext());
//                ChargingReceiver receiver = new ChargingReceiver();
//                receiver.sendTrafficToServer(HomeFragment.this.getContext());
            }
        });


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void stopJobScheduler(Context context)
    {
        JobScheduler jobScheduler =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.cancel(MainActivity.jobSchedulerId);
    }
}