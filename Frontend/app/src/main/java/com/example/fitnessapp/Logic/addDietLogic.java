package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to add meals in diet feature
 */
public class addDietLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    /**
     * the constructor
     * @param i the context
     * @param serverRequest server request which is used to connect to the server
     */
    public addDietLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * This method is used to post food user ate to the server.
     * @param name the name of the food
     * @param calories the calories of the food
     * @param date the date when the food is added
     * @param meal meal of the day
     * @throws JSONException
     */
    public void addDiet(String name, String calories, String date, String meal) throws JSONException {
        String url = "http://coms-309-004.class.las.iastate.edu:8080/diet/"+ UserInfo.getUserID();

        JSONObject newFood = new JSONObject();
        newFood.put("name", name);
        newFood.put("calories", calories);
        newFood.put("date", date);
        newFood.put("meal",meal);
        serverRequest.sendToServer(url,newFood,"Post");

    }
    @Override
    public void onSuccess() {
        i.showText("saved");
    }

    @Override
    public void onError() {
        i.showText("error");
    }
}
