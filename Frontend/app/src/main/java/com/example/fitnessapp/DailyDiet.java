package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyDiet extends AppCompatActivity implements IView{
    LinearLayout layout;
    JSONArray requestArray = new JSONArray();
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diet);
        layout = (LinearLayout) findViewById(R.id.layoutV);
        JSONObject object = new JSONObject();
        Intent intent = getIntent();
        date = intent.getStringExtra("message");
        try {
            object.put("date",date);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        requestArray.put(object);


        makeRequest();
    }

    final String url = "http://coms-309-004.class.las.iastate.edu:8080/diet/29";
    private void makeRequest(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
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
                        ShapeDrawable shapeDrawable = new ShapeDrawable();
                        shapeDrawable.setShape(new RectShape());
                        shapeDrawable.getPaint().setColor(Color.BLUE);
                        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
                        shapeDrawable.getPaint().setStrokeWidth(5);
                        textView.setBackground(shapeDrawable);
                        textView.setGravity(Gravity.CENTER);
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