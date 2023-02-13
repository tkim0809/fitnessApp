package com.example.dicerollexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = findViewById(R.id.rollButton);
        TextView textNumber = findViewById(R.id.textNumber);

        String roll;

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand = new Random().nextInt(6) + 1;
                textNumber.setText(" " + String.valueOf(rand));
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("roll1", String.valueOf(rand));
                startActivity(i);

            }
        });

    }
}