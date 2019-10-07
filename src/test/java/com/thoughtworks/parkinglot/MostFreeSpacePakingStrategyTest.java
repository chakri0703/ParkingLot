package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostFreeSpacePakingStrategyTest {

    @Test
    void givenTwoParkingLots_WhenPark_ThenAbleToParkInMostFreeSpaceLot() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);

        MostFreeSpaceParkingStrategy mostFreeSpaceAttendant = new MostFreeSpaceParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostFreeSpaceAttendant);

        Object vehicle = new Object();
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
        MostFreeSpaceParkingStrategy mostFreeSpaceAttendant = new MostFreeSpaceParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostFreeSpaceAttendant);
        parkingLotOne.park(vehicleOne);
        attendant.park(vehicleTwo);
        assertEquals(vehicleTwo, parkingLotTwo.unPark(vehicleTwo));
    }

    @Test
    void givenThreeParkingLots_WhenPark_ThenAbleParkInMostFreeSpace() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);

        Object vehicle = new Object();

        MostFreeSpaceParkingStrategy mostFreeSpaceAttendant = new MostFreeSpaceParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostFreeSpaceAttendant);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotThree.unPark(vehicle));
    }
    @Test
    void givenThreeParkingLot_WhenPark_ThenAbleParkInMostFreeSpace() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(1);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);

        Object vehicle = new Object();

        MostFreeSpaceParkingStrategy mostFreeSpaceAttendant = new MostFreeSpaceParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostFreeSpaceAttendant);
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotOne.unPark(vehicle));
    }
}
