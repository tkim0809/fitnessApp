package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class profileEditLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    /**
     * constructor
     * @param i
     * @param serverRequest
     */
    public profileEditLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }


    /**
     * This method put/post user profile information which the user has edited
     * @param username
     * @param gender
     * @param age
     * @param weight
     * @param email
     * @throws JSONException
     */
    public void editProfile(String username,String gender, String age, String weight, String email,String userId) throws JSONException {
        final String url = "http://coms-309-004.class.las.iastate.edu:8080/profile/"+userId;
        String meth;
        if (UserInfo.getHasProfile()){
            meth = "Put";
        }
        else {
            meth = "Post";
        }
        JSONObject newProfile = new JSONObject();
        newProfile.put("userName",username);

        newProfile.put("gender",gender);
        newProfile.put("age",age);
        newProfile.put("weight",weight);
        newProfile.put("email",email);
        serverRequest.sendToServer(url,newProfile,meth);


    }

    @Override
    public void onSuccess() {
        i.showText("saved");
    }

    @Override
    public void onError() { i.showText("error");

    }
}
