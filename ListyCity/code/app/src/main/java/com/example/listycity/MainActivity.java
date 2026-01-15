package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;

    EditText cityInput;
    int selectPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);

        Button addCityButton = findViewById(R.id.add_city);
        Button confirmButton = findViewById(R.id.confirm_city);
        Button deleteCityButton = findViewById(R.id.delete_city);
        
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Dehli"};

        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectPos = position;
        });

        // ADD CITY, show input
        addCityButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
        });

        // CONFIRM, add city
        confirmButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty()) {
                datalist.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
            }
        });

        // DELETE CITY, remove selected
        deleteCityButton.setOnClickListener(v -> {
            if (selectPos != -1) {
                datalist.remove(selectPos);
                cityAdapter.notifyDataSetChanged();
                selectPos = -1;
            }
        });
    }
}