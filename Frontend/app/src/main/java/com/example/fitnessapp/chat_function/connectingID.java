package com.example.fitnessapp.chat_function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;

public class connectingID extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting_id);
        EditText id = findViewById(R.id.ConnectIdEdit);
        Button next = findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(connectingID.this,ChatList.class);
                UserInfo.setUserID(id.getText().toString());
                System.out.println(id.getText().toString());
                startActivity(i);
            }
        });
    }
}