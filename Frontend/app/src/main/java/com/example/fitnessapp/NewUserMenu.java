package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NewUserMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_menu);
        View userIconBackground = findViewById(R.id.userIconBackground);
        userIconBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the click action here, such as opening a user profile page
            }
        });
    }
}