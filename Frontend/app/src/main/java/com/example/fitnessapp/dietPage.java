package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dietPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page);
        Button TodayBtn = findViewById(R.id.TodayBtn);
        Button MondayBtn = findViewById(R.id.MondayBtn);
        Button TuesdayBtn = findViewById(R.id.TuesdayBtn);
        Button WednesdayBtn = findViewById(R.id.WednesdayBtn);
        Button ThursdayBtn = findViewById(R.id.ThursdayBtn);
        Button FridayBtn = findViewById(R.id.FridayBtn);
        Button SaturdayBtn = findViewById(R.id.SaturdayBtn);
        Button SundayBtn = findViewById(R.id.SundayBtn);
        Intent intent = new Intent(this,DailyDiet.class);
        DateLogic dateLogic = new DateLogic();
        TodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.getCurrentDate());
                startActivity(intent);

            }
        });
        MondayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateMonday());
                startActivity(intent);
            }
        });
        TuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateTuesday());
                startActivity(intent);
            }
        });
        WednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateWednesday());
                startActivity(intent);
            }
        });
        ThursdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateThursday());
                startActivity(intent);
            }
        });
        FridayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateFriday());
                startActivity(intent);
            }
        });
        SaturdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateSaturday());
                startActivity(intent);
            }
        });
        SundayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateSunday());
                startActivity(intent);
            }
        });

    }
}