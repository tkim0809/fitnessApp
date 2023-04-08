package com.example.fitnessapp.diet_function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Logic.dietGoalLogic;
import com.example.fitnessapp.Logic.layoutLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateRequests;
import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;

import org.json.JSONException;

public class DietGoal extends AppCompatActivity implements IView {
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
                UserInfo.setHasSetDietGoal(true);
                Intent back = new Intent(DietGoal.this,dietPage.class);
                startActivity(back);

            }
        });
        ViewGroup rootView = findViewById(R.id.dietGoalLO);
        layoutLogic.setAllTxtColor(rootView, Color.WHITE);
        layoutLogic.defBtnColor(rootView);


    }

    @Override
    public void showText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}