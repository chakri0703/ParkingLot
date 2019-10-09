package com.thoughtworks.parkinglot.strategy;

import com.thoughtworks.parkinglot.FreeSpaceComparator;
import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.List;

public class MostFreeSpaceParkingStrategy implements ParkingStrategy {

    private List<ParkingLot> parkingLots;

    public MostFreeSpaceParkingStrategy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException {
        parkingLots.sort(new FreeSpaceComparator());
        try {
            parkingLots.get(parkingLots.size() - 1).park(vehicle);
        } catch (ParkingLotFullException e) {
            throw new AllParkingLotsAreFullException();
        }
    }
}
