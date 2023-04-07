package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessapp.Logic.IVolleyListener;
import com.example.fitnessapp.Logic.dietGoalLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateRequests;

import org.json.JSONException;

public class DietGoal extends AppCompatActivity implements IView{
    int weeklyCalNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_goal);
        EditText inputCal = findViewById(R.id.goalCalTxt);
        TextView weeklyCal = findViewById(R.id.weeklyCalTxt);
        Button setBtn = findViewById(R.id.setBtn);
        inputCal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when the text is changed.
                String text = s.toString();
                weeklyCalNum = Integer.parseInt(text)*7;
                weeklyCal.setText(weeklyCalNum+"Cals");
                // Use the text variable here.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text is changed.
            }
        });
        IServerRequest request = new updateRequests();
        final dietGoalLogic logic = new dietGoalLogic(DietGoal.this,request);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayCal = Integer.parseInt(inputCal.getText().toString());
                String weekCal = String.valueOf(weeklyCalNum);
                try {
                    logic.setGoal(dayCal,weekCal);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    @Override
    public void showText(String s) {

    }
}