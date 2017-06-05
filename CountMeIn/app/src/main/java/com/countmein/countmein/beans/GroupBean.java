package com.countmein.countmein.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Home on 6/5/2017.
 */

public class GroupBean extends  BaseModel implements Serializable{

    public String name;
    public String description;
    public List<User> friends;

    public GroupBean(String name, String description, List<User> friends) {
        this.name = name;
        this.description = description;
        this.friends = friends;
    }

    public GroupBean() {
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
