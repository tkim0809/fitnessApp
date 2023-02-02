package com.example.menuexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Page3 extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        home = findViewById(R.id.buttonHome3);
        Intent backHome = new Intent(Page3.this, Menu.class);
        startActivity(backHome);
    }
}