package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fitnessapp.Logic.IVolleyListener;
import com.example.fitnessapp.Logic.addDietLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateRequests;

import org.json.JSONException;

public class addDietPage extends AppCompatActivity implements IView {

    Button add = findViewById(R.id.addBtn);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet_page);

        EditText dateDiet = findViewById(R.id.DateTxt);
        EditText foodDiet = findViewById(R.id.FoodTxt);
        EditText caloriesDiet = findViewById(R.id.CaloriesTxt);
        Spinner dropdown = findViewById(R.id.MealDD);
        String[] items = new String[]{"Breakfast", "Lunch", "Dinner","Snack"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        IServerRequest request = new updateRequests();
        final addDietLogic logic = new addDietLogic(addDietPage.this,request);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateDiet.getText().toString();
                String food = foodDiet.getText().toString();
                String calories = caloriesDiet.getText().toString();
                String meal = dropdown.getSelectedItem().toString();
                try {
                    logic.addDiet(food,calories,date,meal);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void showText(String s) {

    }
}