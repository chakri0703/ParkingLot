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

    public String park(Object vehicle) throws VehicleAlreadyParkedException, ParkingLotFullException {
        if (parkingLotOne.getCapacity() > parkingLotTwo.getCapacity()) {
            parkingLotOne.park(vehicle);
            return "LotOne";
        }
        parkingLotTwo.park(vehicle);
        return "LotTwo";
    }
}
