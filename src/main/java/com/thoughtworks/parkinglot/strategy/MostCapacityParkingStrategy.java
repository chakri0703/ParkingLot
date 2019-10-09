package com.thoughtworks.parkinglot.strategy;

import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.Collections;
import java.util.List;

public class MostCapacityParkingStrategy implements ParkingStrategy {

    private List<ParkingLot> parkingLots;

    public MostCapacityParkingStrategy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException {
        parkingLots.sort(Collections.reverseOrder());
        for (ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.park(vehicle);
                return;
            } catch (ParkingLotFullException e) {
                e.printStackTrace();
            }
        }
        throw new AllParkingLotsAreFullException();
    }
}
