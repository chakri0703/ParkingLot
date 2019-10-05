package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public class Attendant {
    private final ParkingLot parkingLotOne;
    private final ParkingLot parkingLotTwo;

    public Attendant(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        this.parkingLotOne = parkingLotOne;
        this.parkingLotTwo = parkingLotTwo;

    }

    public void park(Object vehicle) throws VehicleAlreadyParkedException, ParkingLotFullException {
        parkingLotOne.park(vehicle);
        parkingLotTwo.park(vehicle);
    }
}
