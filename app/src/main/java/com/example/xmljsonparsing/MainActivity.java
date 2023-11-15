package com.example.xmljsonparsing;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView currencyListView;
    private EditText filterEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyListView = findViewById(R.id.currencyListView);
        filterEditText = findViewById(R.id.filterEditText);

        // Initialize and populate ListView with currency rates
        new DataLoader(this).execute();
    }

    // Make the method package-private
    void updateListView(List<String> currencyRates) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencyRates);
        currencyListView.setAdapter(adapter);
    }
}
