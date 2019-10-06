package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.awt.*;
import java.util.List;

public class MostFreeSpaceAttendant extends Attendant {

    private List<ParkingLot> parkingLots;

    public MostFreeSpaceAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public void park(Object vehicle) throws VehicleAlreadyParkedException, ParkingLotFullException {
        ParkingLot mostFreeSpaceLot = parkingLots.get(0);
        for (int i = 1; i < parkingLots.size(); i++) {
            if (mostFreeSpaceLot.hasMoreSpace(parkingLots.get(i))) {
            } else {
                mostFreeSpaceLot = parkingLots.get(i);
            }
            mostFreeSpaceLot.park(vehicle);

        }
    }

}