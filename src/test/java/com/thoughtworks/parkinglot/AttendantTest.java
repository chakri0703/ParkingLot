package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttendantTest {

    @Test
    void givenParkingLot_WhenPark_ThenAttendantShouldBeAbleToPark() {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
        Attendant attendant = new Attendant(parkingLots);
        Object vehicle = new Object();
        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenParkingLot_WhenParkInMostAvailableCapacity_ThenAttendantShouldAbleToUnParkFromParkingLotTwo() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
        Object vehicle = new Object();
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotTwo.unPark(vehicle));
        assertThrows(NotParkedException.class, () -> parkingLotOne.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenParkInMostAvailableCapacity_ThenAttendantShouldAbleToUnParkFromParkingLotOne() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(3);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
        Object vehicle = new Object();
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotOne.unPark(vehicle));
        assertThrows(NotParkedException.class, () -> parkingLotTwo.unPark(vehicle));
    }


}
