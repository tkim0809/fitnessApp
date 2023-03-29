package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class DailyDiet extends AppCompatActivity implements IView{
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diet);
        layout = (LinearLayout) findViewById(R.id.layoutV);
        makeRequest();
    }
    final String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/dayCal";
    private void makeRequest(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i<response.length(); ++i){
                        JSONObject object = response.getJSONObject(i);
                        String food = object.get("name").toString();
                        String calories = object.get("calories").toString();
                        String meal = object.get("meal").toString();
                        String text = " "+food+"       "+calories+"       "+meal;
                        TextView textView = new TextView(DailyDiet.this);
                        textView.setText(text);
                        textView.setTextSize(20);
                        //set height for textView
                        int heightInPixels = 200;
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                heightInPixels
                        );
                        textView.setLayoutParams(layoutParams);
                        layout.addView(textView);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void showText(String s) {

    }
}