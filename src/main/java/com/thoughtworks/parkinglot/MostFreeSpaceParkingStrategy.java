package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class MostFreeSpaceParkingStrategy implements ParkingStrategy {

    private List<ParkingLot> parkingLots;

    public MostFreeSpaceParkingStrategy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public void park(Object vehicle) throws VehicleAlreadyParkedException, ParkingLotFullException, AllParkingLotsAreFullException {
        parkingLots.sort(new FreeSpaceComparator());
        parkingLots.get(parkingLots.size() - 1).park(vehicle);
    }
}
