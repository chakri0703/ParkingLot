package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.Collections;
import java.util.List;

public class Attendant {

    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void park(Object vehicle) throws VehicleAlreadyParkedException, ParkingLotFullException {
        Collections.max(parkingLots).park(vehicle);
    }
}
