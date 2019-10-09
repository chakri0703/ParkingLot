package com.thoughtworks.parkinglot.strategy;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public interface ParkingStrategy {
    void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException;
}
