package com.countmein.countmein.beans;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zivic on 5/30/2017.
 */

public class GroupInfoBean extends BaseModel implements Serializable{

    private String name;
    private String description;
    private Array friend_ids;

    public GroupInfoBean(String id, String name, String description, Array friend_ids){
        this.name = name;
        this.description = description;
        this.friend_ids = friend_ids;
        this.setId(id);
    }

    public GroupInfoBean( String name, String description, Array friend_ids){
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

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", this.getId());
        result.put("name", name);
        result.put("description", description);
        result.put("friends", friend_ids);


        return result;
    }

}
