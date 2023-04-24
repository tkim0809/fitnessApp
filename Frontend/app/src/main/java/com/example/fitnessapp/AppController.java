package com.example.fitnessapp;

import android.app.Application;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * This class is used to implement request queue to each context.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;
    private static AppController theInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        theInstance = this;
    }

    /**
     * @return the context
     */
    public static synchronized AppController getInstance(){return theInstance;}

    /**
     * @return created or existing request queue
     */
    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * adds request to the request queue
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
