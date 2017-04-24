package com.countmein.countmein.beans;

/**
 * Created by sweet_000 on 4/24/2017.
 */

public class PersonInfoBean {

    private String name;
    private String surname;

    public PersonInfoBean(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
