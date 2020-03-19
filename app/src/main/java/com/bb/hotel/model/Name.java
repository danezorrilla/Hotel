package com.bb.hotel.model;

public class Name {

    private String actualName;

    public Name(String actualName){
        this.actualName = actualName;
    }

    public String getActualName(){return actualName;}

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }
}
