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
        ParkingLot parkingLot = new ParkingLot(1);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() {
        ParkingLot parkingLot = new ParkingLot(2);

        Object object = new Object();

        parkingLot.park(object);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.park(object));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }


}
