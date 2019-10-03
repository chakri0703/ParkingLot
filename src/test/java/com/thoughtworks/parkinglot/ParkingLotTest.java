package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenMustPark() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertTrue(parkingLot.park(new Object()));

    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() {
        ParkingLot parkingLot = new ParkingLot(2);

        parkingLot.park(new Object());
        parkingLot.park(new Object());

        assertEquals(false, parkingLot.park(new Object()));
    }
}
