package com.example.getvalueexp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/weight";
    TextView weightTxt;
    Button getWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightTxt = findViewById(R.id.weightTxt);
        getWeight = findViewById(R.id.getWeightBtn);
        getWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request();
            }
        });
    }

    private void Request() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                weightTxt.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weightTxt.setText("error");
            }
        });
        requestQueue.add(request);



    }
}