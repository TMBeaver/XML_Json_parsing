package com.example.xmljsonparsing;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.List;


public class DataLoader extends AsyncTask<Void, Void, String> {
    private MainActivity mainActivity;

    public DataLoader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.floatrates.com/daily/usd.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String data) {
        if (data != null) {
            // Parse the data using the Parser class
            List<String> currencyRates = new Parser().parseData(data);
            // Update UI in MainActivity
            mainActivity.updateListView(currencyRates);
        }
    }
}


