package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class addDietLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    public addDietLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }
    public void addDiet(String name, String calories, String date, String meal) throws JSONException {
        final String url = "http://coms-309-004.class.las.iastate.edu:8080/diet/35";
        JSONObject newFood = new JSONObject();
        newFood.put("name", name);
        newFood.put("calories", calories);
        newFood.put("date", date);
        newFood.put("meal",meal);
        serverRequest.sendToServer(url,newFood,"Post");

    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
