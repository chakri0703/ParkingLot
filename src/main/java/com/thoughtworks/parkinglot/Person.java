package com.thoughtworks.parkinglot;

public class Person {

    private String message;


    public void inform(String message){
        this.message=message;
    }
    public boolean gotInformed() {
     if (message!=null){
         return true;
     }
     return false;
    }
}
