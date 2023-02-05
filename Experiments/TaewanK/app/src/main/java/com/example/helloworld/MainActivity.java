package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button next, pop, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button) findViewById(R.id.nextButton);
        pop = (Button) findViewById(R.id.popupButton);
        count = (Button) findViewById(R.id.countButton);

        pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "This is a pop up message", Toast.LENGTH_SHORT).show();

            }

        });

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);

            }

        });

        count.setOnClickListener(new View.OnClickListener() {
            int clickCount = 0;
            @Override
            public void onClick(View view) {

                clickCount++;
                if(clickCount == 1) {

                    Toast.makeText(getApplicationContext(), "Button clicked for first time", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Button clicked for " + clickCount + " times", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}