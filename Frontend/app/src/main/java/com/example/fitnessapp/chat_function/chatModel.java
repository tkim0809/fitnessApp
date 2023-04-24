package com.example.fitnessapp.chat_function;

/**
 * This is the chat text box model.
 */
public class chatModel {


    String message;
    Boolean Isent;

    /**
     * @param message the text message
     * @param Isent equals true if the user has sent the message, false if user receive it
     */
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
