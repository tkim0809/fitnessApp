package com.example.fitnessapp.chat_function;

public class chatModel {


    String message;
    Boolean Isent;

    public chatModel(String message, Boolean Isent) {
        this.message = message;
        this.Isent = Isent;
    }
    public String getMessage() {
        return message;
    }

    public Boolean getIsent() {
        return Isent;
    }
}
