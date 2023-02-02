package com.example.menuexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.menuexp.R.id;

public class Page2 extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        home = findViewById(R.id.buttonHome2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backHome = new Intent(Page2.this,Menu.class);
                startActivity(backHome);
            }
        });
    }
}