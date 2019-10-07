package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public class Attendant {
    private ParkingStrategy behaviour;

    public Attendant(ParkingStrategy parkingStrategy) {
        this.behaviour = parkingStrategy;
    }

    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException {
        behaviour.park(vehicle);
    }
}
