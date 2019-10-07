package com.thoughtworks.parkinglot;

import java.util.Comparator;

public class FreeSpaceComparator implements Comparator<ParkingLot> {
    @Override
    public int compare(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        return (parkingLotOne.getCapacity()-parkingLotOne.getVehicleSize())-(parkingLotTwo.getCapacity()-parkingLotTwo.getVehicleSize());
    }
}
