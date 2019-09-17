package com.example.wallshaveears.ui.datastatistics;

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

public class DataStatisticsFragment extends Fragment {

    private DataStatisticsViewModel dataStatisticsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataStatisticsViewModel =
                ViewModelProviders.of(this).get(DataStatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_datastatistics, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        dataStatisticsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    /*
    package com.example.graphtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.graphtutorial.Model.Person;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    PieChart chart ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    private List<PieEntry> entries;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initChartView();
    }

    public void getPersonData() {
        String urlString = "https://api.myjson.com/bins/g647p";
        ArrayList persons = new ArrayList();
        try {
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine() + "\n";
                }
                JSONObject objJSON = new JSONObject(inline);
                JSONArray data = objJSON.getJSONArray("data");
                persons = convertJSONtoPersons(data);
                this.persons = persons;
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Person> convertJSONtoPersons(JSONArray jsonPersons) throws JSONException {
        int noOfPersons = jsonPersons.length();
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < noOfPersons; i++) {
            JSONArray person = jsonPersons.getJSONArray(i);
            String name = person.getString(0);
            int age = person.getInt(1);
            int sallary = person.getInt(2);
            persons.add(new Person(name, age, sallary));
        }
        return persons;
    }


    private void initChartData() {
        getPersonData();

        int below10000 = 0;
        int between10000and30000 = 0;
        int between30000and40000 = 0;
        int above40000 = 0;

        for(Person person : persons) {
            double sallary = person.getAnnualSallary();
            if(sallary < 10000) {
                below10000++;
            } else if(sallary >= 10000 && sallary < 30000) {
                between10000and30000++;
            } else if(sallary >= 30000 && sallary <= 40000) {
                between30000and40000++;
            } else {
                above40000++;
            }
        }

        double percBelow = 100/below10000;
        double percBetween1 = 100 / between10000and30000;
        double percBetween2 = 100/ between30000and40000;
        double percAbove = 100/above40000;

        entries = new ArrayList<>();
        entries.add(new PieEntry(below10000, "Below $10k"));
        entries.add(new PieEntry(between10000and30000, "$10k <= x < $30k"));
        entries.add(new PieEntry(between30000and40000, "$30k <= x <= $40k"));
        entries.add(new PieEntry(above40000, "Above $40k"));



    }

    @SuppressLint("StaticFieldLeak")
    private void initChartView() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                initChartData();
                return true;
            }

            @Override
            protected void onPostExecute(Boolean bool) {
                initChart();
            }
        }.execute();
    }

    private void initChart() {
        chart = (PieChart) findViewById(R.id.chart);
        pieDataSet = new PieDataSet(entries, "Income in Djibouti");
        pieData = new PieData(pieDataSet);

        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(pieData);
        chart.invalidate();


    }




}

     */
}