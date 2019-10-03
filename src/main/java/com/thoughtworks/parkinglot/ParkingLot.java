package com.thoughtworks.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private int capacity;

    Set<Object> vehicles = new HashSet<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }


    public boolean park(Object object) {
        if (isParkingLotFull()) {
            return false;
        }

        return vehicles.add(object);
    }

    private boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }
}
