package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostFreeSpaceAttendantTest {

    @Test
    void givenTwoParkingLots_WhenPark_ThenAbleToParkInMostFreeSpaceLot() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);

        Object vehicle = new Object();

        MostFreeSpaceAttendant attendant = new MostFreeSpaceAttendant(parkingLots);
        attendant.park(vehicle);

        assertEquals(vehicle, parkingLotOne.unPark(vehicle));
    }

    @Test
    void givenTwoParkingLotsWithSameCapacity_WhenPark_ThenShouldAbleToParkInMostFreeSpace() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        MostFreeSpaceAttendant attendant = new MostFreeSpaceAttendant(parkingLots);
        parkingLotOne.park(vehicleOne);
        attendant.park(vehicleTwo);
        assertEquals(vehicleTwo, parkingLotTwo.unPark(vehicleTwo));
    }

    @Test
    void givenThreeParkingLots_WhenPark_ThenAbleParkInMostFreeSpace() throws NotParkedException, ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);

        Object vehicle = new Object();

        MostFreeSpaceAttendant attendant = new MostFreeSpaceAttendant(parkingLots);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotThree.unPark(vehicle));
    }
}
