package com.example.fitnessapp.Logic;



import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class workoutHistoryUpdateLogic implements IVolleyListener {
    IView i;
    IServerRequest serverRequest;

    /**
     * constructor
     * @param i
     * @param serverRequest
     */
    public workoutHistoryUpdateLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }


    /**
     * This method post user's work out history to the server.
     * @param name
     * @param sets
     * @param reps
     * @param weight
     * @throws JSONException
     */
    public void addExercise(String name,String sets, String reps, String weight) throws JSONException {
        final String url = "http://coms-309-004.class.las.iastate.edu:8080/stats/new";
        //JSONArray exArray = new JSONArray();
        JSONObject newExercise = new JSONObject();
        newExercise.put("workoutName",name);
        newExercise.put("workoutSets",sets);
        newExercise.put("workoutReps",reps);
        newExercise.put("workoutWeight",weight);
        //exArray.put(newExercise);

        serverRequest.sendToServer(url,newExercise,"Post");


    }

    @Override
    public void onSuccess() {
            i.showText("Saved");
    }

    @Override
    public void onError() {
        i.showText("Error");
    }
}
