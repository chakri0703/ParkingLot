package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendantTest {

    @Test
    void givenParkingLot_WhenPark_ThenAttendantShouldBeAbleToPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        Attendant attendant = new Attendant(parkingLot, parkingLotTwo);
        Object vehicle = new Object();
        assertDoesNotThrow(() -> {
            attendant.park(vehicle);
        });
    }
}
