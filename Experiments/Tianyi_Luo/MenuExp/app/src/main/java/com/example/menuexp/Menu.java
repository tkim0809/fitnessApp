package com.example.menuexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button pg1, pg2, pg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pg1 = findViewById(R.id.buttonPG1);
        pg2 = findViewById(R.id.buttonPG2);
        pg3 = findViewById(R.id.buttonPG3);
        pg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPG1 = new Intent(Menu.this, Page1.class);
                startActivity(toPG1);
            }
        });
        pg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPG2 = new Intent(Menu.this, Page2.class);
                startActivity(toPG2);
            }
        });
        pg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPG3 = new Intent(Menu.this,Page3.class);
                startActivity(toPG3);
            }
        });
    }
}