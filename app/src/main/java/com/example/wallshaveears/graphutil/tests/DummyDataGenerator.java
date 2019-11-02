package com.example.wallshaveears.graphutil.tests;

import com.example.wallshaveears.database.entities.Traffic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyDataGenerator {

    private Random random = new Random();

    public List<Traffic> generateDummyData(int amount) {
        List<Traffic> randomTrafficList = new ArrayList<>();


        //String[] appNames = new String[]{"Lasses Shady App", "Robert Analytics", "Google", "Facebook", "Spotify", "Audible", "KølleIQ", "KølbækAnalytiC", "NorbyPsyk", "Temple Run"};
        String[] appNames = new String[]{"Lasses Shady App", "Robert Analytics", "Google", "Facebook", "Spotify"};
        //String[] appNames = new String[]{"Lasses Shady App", "Robert Analytics", "Google"};



        long dateIncrease = 50000;
        for(int i = 0; i < appNames.length; i++) {
            String appName = appNames[i];
            for(int j = 0; j < amount; j++) {

                int min = ((i+1) * 3) * 10;
                int max = ((i+1) * 3) * 11;

                long randomRx = (long)random.nextInt(max) + min;
                long randomTx = (long)random.nextInt(max) + min;
                Traffic randomTraffic = generateRandomTrafficObject(dateIncrease, appName, randomRx, randomTx);
                randomTrafficList.add(randomTraffic);
                dateIncrease += dateIncrease;

            }
        }
        return randomTrafficList;

    }

    private Traffic generateRandomTrafficObject(long dateIncrease, String appName, long randomRx, long randomTx) {
        Date date = new Date(1570781692);
        date.setTime(date.getTime() + dateIncrease);
        return new Traffic(appName, date, randomRx, randomTx, 5,5,5);
    }

}
