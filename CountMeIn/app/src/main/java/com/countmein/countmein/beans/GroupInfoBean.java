package com.countmein.countmein.beans;

import java.lang.reflect.Array;

/**
 * Created by zivic on 5/30/2017.
 */

public class GroupInfoBean {

    private String name;
    private String description;
    private Array friend_ids;

    public GroupInfoBean(String name, String description, Array friend_ids){
        this.name = name;
        this.description = description;
        this.friend_ids = friend_ids;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Array getFriend_ids(){
        return friend_ids;
    }

    public void setFriend_ids(Array friend_ids){
        this.friend_ids = friend_ids;
    }

}
