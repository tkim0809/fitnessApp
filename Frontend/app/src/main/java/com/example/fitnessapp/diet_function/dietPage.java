package com.example.fitnessapp.diet_function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import com.example.fitnessapp.NewUserMenu;
import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;
import com.example.fitnessapp.userMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class dietPage extends AppCompatActivity {
    TextView mondayInfo;
    TextView tuesdayInfo;
    TextView wednesdayInfo;
    TextView thursdayInfo;
    TextView fridayInfo;
    TextView saturdayInfo;
    TextView sundayInfo;
    TextView todayInfo;
    TextView MondayPct;
    TextView TuesdayPct;
    TextView WednesdayPct;
    TextView ThursdayPct;
    TextView FridayPct;
    TextView SaturdayPct;
    TextView SundayPct;
    TextView TodayPct;

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
                Intent back = new Intent(dietPage.this, NewUserMenu.class);
                startActivity(back);
            }
        });
        Intent intent = new Intent(this, DailyDiet.class);
        DateLogic dateLogic = new DateLogic();
        TodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.getCurrentDate());
                startActivity(intent);

            }
        });
        MondayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateMonday());
                startActivity(intent);
            }
        });
        TuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateTuesday());
                startActivity(intent);
            }
        });
        WednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateWednesday());
                startActivity(intent);
            }
        });
        ThursdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateThursday());
                startActivity(intent);
            }
        });
        FridayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateFriday());
                startActivity(intent);
            }
        });
        SaturdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateSaturday());
                startActivity(intent);
            }
        });
        SundayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("message", dateLogic.DateSunday());
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

        MondayPct = findViewById(R.id.MondayPct);
        TuesdayPct = findViewById(R.id.TuesdayPct);
        WednesdayPct = findViewById(R.id.WednesdayPct);
        ThursdayPct = findViewById(R.id.ThursdayPct);
        FridayPct = findViewById(R.id.FridayPct);
        SaturdayPct = findViewById(R.id.SaturdayPct);
        SundayPct = findViewById(R.id.SundayPct);
        TodayPct = findViewById(R.id.todayPct);


        Boolean[] isFuture = dateLogic.getFuture();
            try {
                getDataForDay(todayInfo, dateLogic.getCurrentDate(),TodayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        try {
            if (isFuture[1]) {
                mondayInfo.setText("Add meals later");
                MondayPct.setText("");
            } else {
                getDataForDay(mondayInfo, dateLogic.DateMonday(),MondayPct);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if (isFuture[2]) {
            tuesdayInfo.setText("Add meals later");
            TuesdayPct.setText("");
        } else {
            try {
                getDataForDay(tuesdayInfo, dateLogic.DateTuesday(),TuesdayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }


        // Check if Wednesday is in the future
        if (isFuture[3]) {
            wednesdayInfo.setText("Add meals later");
            WednesdayPct.setText("");
        } else {
            try {
                getDataForDay(wednesdayInfo, dateLogic.DateWednesday(),WednesdayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Thursday is in the future
        if (isFuture[4]) {
            thursdayInfo.setText("Add meals later");
            ThursdayPct.setText("");
        } else {
            try {
                getDataForDay(thursdayInfo, dateLogic.DateThursday(),ThursdayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Friday is in the future
        if (isFuture[5]) {
            fridayInfo.setText("Add meals later");
            FridayPct.setText("");
        } else {
            try {
                getDataForDay(fridayInfo, dateLogic.DateFriday(),FridayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Saturday is in the future
        if (isFuture[6]) {
            saturdayInfo.setText("Add meals later");
            SaturdayPct.setText("");
        } else {
            try {
                getDataForDay(saturdayInfo, dateLogic.DateSaturday(),SaturdayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

// Check if Sunday is in the future
        if (isFuture[0]) {
            sundayInfo.setText("Add meals later");
            SundayPct.setText("");
        } else {
            try {
                getDataForDay(sundayInfo, dateLogic.DateSunday(),SundayPct);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }





        Button addMealBtn = findViewById(R.id.addMealBtn);
        Button setGoalBtn = findViewById(R.id.setGoalBtn);
        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddDiet = new Intent(dietPage.this, addDietPage.class);
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



    }


    public void getDataForDay(TextView dayInfo, String date,TextView dayPct) throws JSONException {
        String url = "http://coms-309-004.class.las.iastate.edu:8080/diet?date="+date+"&userId="+UserInfo.getUserID();
        /**
         * postman test url
         */
        //String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/diet?date=" + date + "&userId=" + UserInfo.getUserID();
        System.out.println(url);
        String userId = UserInfo.getUserID();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());

                        try {
                            int totalIn = (int) response.get("totalCalories");
                            dayInfo.setText(response.get("totalCalories").toString() + "Cal");
                            System.out.println("Total request for" + date + "succeed total is"+totalIn);

                            //get percentage
                            getPctForDay(dayPct,totalIn);

                            System.out.println("request for" + date + "succeed");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dayInfo.setText(" 0Cal");
                dayPct.setText(" 0% of total");
                System.out.println(error.toString());
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);


    }

    public void getPctForDay(TextView dayPct, int totalIn) throws JSONException {
        String url = "http://coms-309-004.class.las.iastate.edu:8080/dietgoal/"+UserInfo.getUserID();
        /**
         * postman url
         */
        //String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/dietgoal/" + UserInfo.getUserID();
        System.out.println(url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            dayPct.setText(calculatePct(totalIn, response.getInt("dietGoalValue")) + "% of plan");
                            System.out.println("Pct request" + "succeed");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());

            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);


    }

    public String calculatePct(int a, int b) {
        float i = (float) a;
        float j = (float) b;
        float result = i / j * 100;
        String formatted = String.format("%.1f", result);
        System.out.println(a+ "divided by" +b + "="+ formatted+"%");
        return formatted +"%";

    }

}