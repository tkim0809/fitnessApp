package com.example.fitnessapp.diet_function;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.IView;
import com.example.fitnessapp.Logic.layoutLogic;
import com.example.fitnessapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyDiet extends AppCompatActivity implements IView {
    ScrollView layout;
    JSONArray requestArray = new JSONArray();
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_diet);
        Button back = findViewById(R.id.backBtn);
        back.setText("\u2190back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(DailyDiet.this,dietPage.class);
                startActivity(back);
            }
        });
        layout = (ScrollView) findViewById(R.id.layoutV);
        JSONObject object = new JSONObject();
        Intent intent = getIntent();
        date = intent.getStringExtra("message");
        try {
            object.put("date",date);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        requestArray.put(object);

        ViewGroup rootView = findViewById(R.id.dailyDietLO);
        layoutLogic.defBtnColor(rootView);
        makeRequest();



// Create a SimpleDateFormat instance to parse the original date string
        SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date unformatedDate = null;
        try {
            unformatedDate = originalDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

// Create another SimpleDateFormat instance to format the date into the desired string format
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = targetDateFormat.format(unformatedDate);
    }

    final String url = "http://coms-309-004.class.las.iastate.edu:8080/diet/29/"+date;
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
                        shapeDrawable.getPaint().setColor(Color.BLACK);
                        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
                        shapeDrawable.getPaint().setStrokeWidth(5);
                        textView.setBackground(shapeDrawable);
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(16);
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
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}