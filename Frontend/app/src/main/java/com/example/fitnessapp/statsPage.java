package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class statsPage extends AppCompatActivity {
    Button editBtn;
    TextView ex1Name;
    TextView ex1Rep;
    TextView ex1Max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(statsPage.this, statsEdit.class);
                startActivity(toEdit);
            }
        });

        ex1Name = findViewById(R.id.ex1Txt);
        ex1Rep = findViewById(R.id.ex1RepTxt);
        ex1Max = findViewById(R.id.ex1MaxTxt);
    }
}