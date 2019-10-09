package com.thoughtworks.parkinglot.attendant;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import com.thoughtworks.parkinglot.strategy.ParkingStrategy;

public class Attendant {
    private ParkingStrategy parkingStrategy;

    public Attendant(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException {
        parkingStrategy.park(vehicle);
    }
}
