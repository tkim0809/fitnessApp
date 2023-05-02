package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.Logic.DateLogic;
import com.example.fitnessapp.chat_function.chatPage;
import com.example.fitnessapp.diet_function.dietPage;
import com.example.fitnessapp.workoutHistory_function.workoutHistoryPage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * User Menu UI class
 */
public class NewUserMenu extends AppCompatActivity {
    TextView DailyCal;
    TextView TodayPct, userName;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_menu);
        Button logOutBtn = findViewById(R.id.logOutBtn);
        userName =findViewById(R.id.textUserName);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(NewUserMenu.this, LoginPage.class);
                startActivity(logOut);
            }
        });
        Button recordBtn = findViewById(R.id.recordBtn);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRecord = new Intent(NewUserMenu.this, workoutHistoryPage.class);
                startActivity(toRecord);
            }
        });
        ImageButton profileBtn = findViewById(R.id.profileBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(NewUserMenu.this, profilePage.class);
                startActivity(toProfile);
            }
        });
        Button dietBtn = findViewById(R.id.dietBtn);
        dietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDiet = new Intent(NewUserMenu.this, dietPage.class);
                startActivity(toDiet);
            }
        });

        Button leaderboardBtn = findViewById(R.id.leaderboardBtn);

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewUserMenu.this, leaderboard.class);
                startActivity(i);
            }
        });
        Button chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toChat = new Intent(NewUserMenu.this, chatPage.class);
                startActivity(toChat);
            }
        });
        Button friendButton = findViewById(R.id.findFriendButton);
        Button friendListButton = findViewById(R.id.FriendListButtonXML);
        friendButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent i = new Intent(NewUserMenu.this, findFriendActivity.class);
                startActivity(i);

            }

        });

        friendListButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewUserMenu.this, friendList.class);
                startActivity(i);
            }
        }) ;
        Button mileStoneBtn = findViewById(R.id.milestoneBtn);
        mileStoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(NewUserMenu.this,milestone.class);
                startActivity(i);
            }
        });
        DailyCal = findViewById(R.id.dailyCalTxt);
        try {
            getDailyCalGoal();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        progressBar = findViewById(R.id.progressBar);

        TodayPct = findViewById(R.id.TodayPct);
        DateLogic dateLogic = new DateLogic();
        try {
            getDataForDay(TodayPct,dateLogic.getCurrentDate());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    public void getDailyCalGoal() throws JSONException {
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
                            DailyCal.setText(response.getInt("dietGoalValue")+"Cal");
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

    /**
     * get and display the diet percentage of today
     * @param todayPct diet percentage reached
     * @param date
     * @throws JSONException
     */
    public void getDataForDay(TextView todayPct,String date) throws JSONException {
        String url = "http://coms-309-004.class.las.iastate.edu:8080/diet?date="+date+"&userId="+UserInfo.getUserID();
        /**
         * postman test url
         */
        //String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/diet?date=" + date + "&userId=" + UserInfo.getUserID();
        System.out.println(url);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());

                        try {
                            int totalIn = (int) response.get("totalCalories");

                            System.out.println("Total request for" + date + "succeed total is"+totalIn);
                            getPctForDay(todayPct,totalIn);


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                todayPct.setText("You have reached 0% of daily plan");

                System.out.println(error.toString());
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);


    }

    /**
     * get diet intake of the day and calculate percentage from the total.
     * @param dayPct view to display
     * @param totalIn total intake
     * @throws JSONException
     */
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
                            progressBar.setProgress(calculatePctInt(totalIn, response.getInt("dietGoalValue")));
                            dayPct.setText("You have reached"+calculatePct(totalIn, response.getInt("dietGoalValue")) + " of daily plan");
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

    /**
     * calculate percentage a/b
     * @param a
     * @param b
     * @return String exp: "10.1%"
     */
    public String calculatePct(int a, int b) {
        float i = (float) a;
        float j = (float) b;
        float result = i / j * 100;
        String formatted = String.format("%.1f", result);
        System.out.println(a+ "divided by" +b + "="+ formatted+"%");
        return formatted +"%";

    }

    /**
     * calculate percentage a/b
     * @param a
     * @param b
     * @return integer
     */
    public int calculatePctInt(int a, int b) {
        float i = (float) a;
        float j = (float) b;
        float result = i / j * 100;
        int intValue = Float.valueOf(result).intValue();
        return intValue;

    }
}