package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendantTest {

    @Test
    void givenParkingLot_WhenPark_ThenAttendantShouldBeAbleToPark() {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        Attendant attendant = new Attendant(parkingLotOne, parkingLotTwo);
        Object vehicle = new Object();
        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenParkingLot_WhenParkInMostAvailableCapacity_ThenAttendantShouldAbleToPark() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        Object vehicle = new Object();
        Attendant attendant = new Attendant(parkingLotOne, parkingLotTwo);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotTwo.unPark(vehicle));
        assertThrows(NotParkedException.class, () -> parkingLotOne.unPark(vehicle));
    }
}
