package com.example.xmljsonparsing;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.List;


public class DataLoader extends AsyncTask<Void, Void, List<String>> {
    private MainActivity mainActivity;

    public DataLoader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.floatrates.com/daily/usd.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return new Parser().parseData(scanner.hasNext() ? scanner.next() : "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> data) {
        if (data != null) {
            // Update UI in MainActivity
            mainActivity.updateListView(data);
        }
    }
}


