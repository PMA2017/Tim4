package com.countmein.countmein.beans;

import android.widget.BaseAdapter;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zivic on 4/23/2017.
 */


public class ActivityBean  extends BaseModel implements Serializable{
    public String name;
    public String description;
    public String date;
    public String lLng;
    public String lLat;
    public GroupBean group;

    public ActivityBean(String name, String description, String date, String lLng, String lLat, GroupBean group) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.lLng = lLng;
        this.lLat = lLat;
        this.group = group;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }



    public ActivityBean() {
    }

    public ActivityBean(String id, String name, String description, String date, String lLat, String lLng){
        super(id);
        this.name = name;
        this.description = description;
        this.date = date;
        this.lLng = lLng;
        this.lLat = lLat;

    }

    public ActivityBean(String name, String description, String date, String lLat, String lLng){
        this.name = name;
        this.description = description;
        this.date = date;
        this.lLng = lLng;
        this.lLat = lLat;
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

    public String getlLng() {
        return lLng;
    }

    public void setlLng(String lLng) {
        this.lLng = lLng;
    }

    public String getlLat() {
        return lLat;
    }

    public void setlLat(String lLat) {
        this.lLat = lLat;
    }

    @Exclude

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.getId());
        result.put("name", name);
        result.put("description", description);
        result.put("date", date);
        result.put("lLat", lLat);
        result.put("lLng", lLng);

        return result;
    }

}
