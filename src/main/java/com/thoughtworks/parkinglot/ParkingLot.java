package com.thoughtworks.parkinglot;

public class ParkingLot {

    private int capacity;
    private int count=0;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean park(Object object){
        if(count<= capacity){
            return true;
        }
        return false;
    }
}
