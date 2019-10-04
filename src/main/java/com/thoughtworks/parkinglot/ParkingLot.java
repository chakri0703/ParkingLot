package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private int capacity;

    Set<Object> vehicles = new HashSet<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }


    public void park(Object object) throws ParkingLotFullException, VehicleAlreadyParkedException {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException("parking Lot is Full");
        }

        if (isParked(object)) {
            throw new VehicleAlreadyParkedException("vehicle already parked");
        }
        vehicles.add(object);
    }

    private boolean isParked(Object object) {
        return vehicles.contains(object);
    }

    protected boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }

    public Object unPark(Object vehicle) throws NotParkedException {
        if (!isParked(vehicle)||vehicles.isEmpty()) {
            throw new NotParkedException("Vehicle Not parked");

        }
        vehicles.remove(vehicle);
        return vehicle;
    }
}
