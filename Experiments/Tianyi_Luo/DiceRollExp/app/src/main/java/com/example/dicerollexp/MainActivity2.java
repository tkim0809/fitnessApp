package com.example.dicerollexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView firstRoll = findViewById(R.id.textFirst);
        Intent i =  getIntent();
        firstRoll.setText("Your first roll is: "+ i.getStringExtra("roll1"));
        Button roll2 = findViewById(R.id.rollButton2);
        TextView r2 = findViewById(R.id.textNumber2);
        roll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand = new Random().nextInt(6) + 1;
                r2.setText(String.valueOf(rand));
            }
        });
    }
}