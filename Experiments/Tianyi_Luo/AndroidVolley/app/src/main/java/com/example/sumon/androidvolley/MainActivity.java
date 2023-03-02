package com.example.sumon.androidvolley;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity implements OnClickListener {
    private Button btnJson, btnString, btnImage;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnString = (Button) findViewById(R.id.btnStringRequest);
        btnJson = (Button) findViewById(R.id.btnJsonRequest);
        btnImage = (Button) findViewById(R.id.btnImageRequest);
        mTextViewResult = findViewById(R.id.text_view_result);

        // button click listeners
        btnString.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnImage.setOnClickListener(this);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });





    }

    private void jsonParse() {

        String url = "/repositories";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = response.getJSONArray("employees");
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStringRequest:
                startActivity(new Intent(MainActivity.this,
                        StringRequestActivity.class));
                break;
            case R.id.btnJsonRequest:
                startActivity(new Intent(MainActivity.this,
                        JsonRequestActivity.class));
                break;
            case R.id.btnImageRequest:
                startActivity(new Intent(MainActivity.this,
                        ImageRequestActivity.class));
                break;
            default:
                break;
        }
    }

}
