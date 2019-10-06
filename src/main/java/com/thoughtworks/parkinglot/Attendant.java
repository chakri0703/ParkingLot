package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

abstract class Attendant {
    public abstract void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException;
}
