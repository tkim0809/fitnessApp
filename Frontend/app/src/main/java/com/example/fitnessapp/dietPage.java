package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.Logic.DateLogic;

import org.json.JSONException;
import org.json.JSONObject;

public class dietPage extends AppCompatActivity {
    TextView mondayInfo;
    TextView tuesdayInfo;
    TextView wednesdayInfo;
    TextView thursdayInfo;
    TextView fridayInfo;
    TextView saturdayInfo;
    TextView sundayInfo;
    TextView todayInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page);
        Button TodayBtn = findViewById(R.id.TodayBtn);
        Button MondayBtn = findViewById(R.id.MondayBtn);
        Button TuesdayBtn = findViewById(R.id.TuesdayBtn);
        Button WednesdayBtn = findViewById(R.id.WednesdayBtn);
        Button ThursdayBtn = findViewById(R.id.ThursdayBtn);
        Button FridayBtn = findViewById(R.id.FridayBtn);
        Button SaturdayBtn = findViewById(R.id.SaturdayBtn);
        Button SundayBtn = findViewById(R.id.SundayBtn);
        Intent intent = new Intent(this,DailyDiet.class);
        DateLogic dateLogic = new DateLogic();
        TodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.getCurrentDate());
                startActivity(intent);

            }
        });
        MondayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateMonday());
                startActivity(intent);
            }
        });
        TuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateTuesday());
                startActivity(intent);
            }
        });
        WednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateWednesday());
                startActivity(intent);
            }
        });
        ThursdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateThursday());
                startActivity(intent);
            }
        });
        FridayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateFriday());
                startActivity(intent);
            }
        });
        SaturdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateSaturday());
                startActivity(intent);
            }
        });
        SundayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message",dateLogic.DateSunday());
                startActivity(intent);
            }
        });
        mondayInfo = findViewById(R.id.MondayCal);
        tuesdayInfo = findViewById(R.id.TuesdayCal);
        wednesdayInfo = findViewById(R.id.WednesdayCal);
        thursdayInfo = findViewById(R.id.ThursdayCal);
        fridayInfo = findViewById(R.id.FridayCal);
        saturdayInfo = findViewById(R.id.SaturdayCal);
        sundayInfo = findViewById(R.id.SundayCal);
        todayInfo = findViewById(R.id.TodayCal);
        try {
            getDataForDay(todayInfo,dateLogic.getCurrentDate());
            getDataForDay(mondayInfo,dateLogic.DateMonday());
            getDataForDay(tuesdayInfo,dateLogic.DateTuesday());
            getDataForDay(wednesdayInfo, dateLogic.DateWednesday());
            getDataForDay(thursdayInfo, dateLogic.DateThursday());
            getDataForDay(fridayInfo, dateLogic.DateFriday());
            getDataForDay(saturdayInfo, dateLogic.DateSaturday());
            getDataForDay(sundayInfo, dateLogic.DateSunday());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
    public void getDataForDay(TextView day, String date) throws JSONException {
        JSONObject requestInfo = new JSONObject();
        String userId = UserInfo.getUserID();
        requestInfo.put("date",date);
        requestInfo.put("userId",userId);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/profile", requestInfo,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            day.setText(response.get("totalCalories").toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);


    }
}