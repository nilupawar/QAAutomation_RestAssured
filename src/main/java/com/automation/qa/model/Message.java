package com.automation.qa.model;

import java.util.List;

public class Message {
    long id;
    String text;
    List<Double> geo;
    User user;

    public Message(long id, String text, List<Double> geo, User user){
        this.id = id;
        this.text = text;
        this.geo = geo;
        this.user = user;
    }

    @Override
    public String toString(){

        return String.format(" text : %s, user : %s",text, user.name);
    }
}
