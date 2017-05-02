package com.countmein.countmein.beans;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zivic on 4/23/2017.
 */


public class ActivityBean {
    public String name;
    public String description;
    public String date;


    public ActivityBean() {
    }

    public ActivityBean(String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("date", date);

        return result;
    }



}
