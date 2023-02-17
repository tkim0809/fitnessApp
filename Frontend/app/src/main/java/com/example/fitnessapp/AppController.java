package com.example.fitnessapp;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    private RequestQueue requestQueue;
    private static AppController theInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        theInstance = this;
    }
    public static AppController getInstance(){return theInstance;}

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public <T>void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
