package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Declear the variables so that you will be able to reference it later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addButton;
    Button deleteButton;

    int selectedIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);


        String []cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);
        addButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (newCity.isEmpty()) return;

            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
            cityInput.setText("");
        });
        deleteButton.setOnClickListener(v -> {
            if (selectedIndex == -1) return;

            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();
            selectedIndex = -1;
        });
        cityList.setOnItemClickListener((parent, view, position, id) -> {selectedIndex = position;});

    }
}
