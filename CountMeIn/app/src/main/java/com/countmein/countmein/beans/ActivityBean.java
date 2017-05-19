package com.countmein.countmein.beans;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zivic on 4/23/2017.
 */


public class ActivityBean  implements Serializable{
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
