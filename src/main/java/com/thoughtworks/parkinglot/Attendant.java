package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public class Attendant {
    private ParkingStrategy parkingStrategy;

    public Attendant(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException {
        parkingStrategy.park(vehicle);
    }
}
