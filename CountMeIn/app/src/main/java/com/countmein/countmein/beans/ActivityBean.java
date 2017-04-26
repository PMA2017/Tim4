package com.countmein.countmein.beans;

import java.util.Date;

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



}
