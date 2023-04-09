package com.example.fitnessapp.diet_function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.Logic.DateLogic;
import com.example.fitnessapp.Logic.layoutLogic;
import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;
import com.example.fitnessapp.userMenu;

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
        Button back = findViewById(R.id.BackBtn);
        back.setText("\u2190back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(dietPage.this, userMenu.class);
                startActivity(back);
            }
        });
        Intent intent = new Intent(this, DailyDiet.class);
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
        Boolean[] isFuture = dateLogic.getFuture();
        if (UserInfo.getHasUpDatedDiet()) {
            try {
                getDataForDay(todayInfo, dateLogic.getCurrentDate());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else {
                todayInfo.setText("    0Cal");
            }

        try {
            if(isFuture[1]){
                mondayInfo.setText("Add meals later");
            }else {
                getDataForDay(mondayInfo,dateLogic.DateMonday());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if(isFuture[2]){
            tuesdayInfo.setText("Add meals later");
        }else {
            try {
                getDataForDay(tuesdayInfo,dateLogic.DateTuesday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }


        // Check if Wednesday is in the future
        if(isFuture[3]) {
            wednesdayInfo.setText("Add meals later");
        } else {
            try {
                getDataForDay(wednesdayInfo, dateLogic.DateWednesday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Thursday is in the future
        if(isFuture[4]) {
            thursdayInfo.setText("Add meals later");
        } else {
            try {
                getDataForDay(thursdayInfo, dateLogic.DateThursday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Friday is in the future
        if(isFuture[5]) {
            fridayInfo.setText("Add meals later");
        } else {
            try {
                getDataForDay(fridayInfo, dateLogic.DateFriday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Saturday is in the future
        if(isFuture[6]) {
            saturdayInfo.setText("Add meals later");
        } else {
            try {
                getDataForDay(saturdayInfo, dateLogic.DateSaturday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Sunday is in the future
        if(isFuture[0]) {
            sundayInfo.setText("Add meals later");
        } else {
            try {
                getDataForDay(sundayInfo, dateLogic.DateSunday());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        Button addMealBtn = findViewById(R.id.addMealBtn);
        Button setGoalBtn = findViewById(R.id.setGoalBtn);
        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddDiet= new Intent(dietPage.this, addDietPage.class);
                startActivity(toAddDiet);
            }
        });
        setGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDietGoal = new Intent(dietPage.this, DietGoal.class);
                startActivity(toDietGoal);
            }
        });
        ViewGroup rootView = findViewById(R.id.dietOL);
        LinearLayout LL = findViewById(R.id.LinearLayout);
        layoutLogic.setAllTxtColor(LL,Color.WHITE);
        layoutLogic.defBtnColor(LL);
        layoutLogic.setAllTxtColor(rootView, Color.WHITE);
        layoutLogic.defBtnColor(rootView);
        TodayBtn.setBackgroundColor(Color.WHITE);


    }
    public void getDataForDay(TextView day, String date) throws JSONException {
        final String url = "http://coms-309-004.class.las.iastate.edu:8080/diet?date="+date+"&userId="+UserInfo.getUserID();
        /**
         * postman test url
         */
        //final String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/diet?date="+date+"&userId="+UserInfo.getUserID();
        JSONObject requestInfo = new JSONObject();
        String userId = UserInfo.getUserID();
        requestInfo.put("date",date);
        requestInfo.put("userId",userId);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, requestInfo,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            day.setText(response.get("totalCalories").toString()+"Cal"+"    "+response.getString("achievedPercentage").toString()+"% of the diet plan");
                            System.out.println("request for"+date+"succeed");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                day.setText(" 0Cal"+"    "+" 0% of the diet plan");
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);


    }
}