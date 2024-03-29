package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.attendant.Attendant;
import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.strategy.MostCapacityParkingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostCapacityParkingStrategyTest {

    @Test
    void givenParkingLot_WhenPark_ThenAttendantShouldBeAbleToPark() {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);
        Object vehicle = new Object();
        assertDoesNotThrow(() -> attendant.park(vehicle));
    }

    @Test
    void givenParkingLot_WhenParkInMostAvailableCapacity_ThenAttendantShouldAbleToUnParkFromParkingLotTwo() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(2);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);
        Object vehicle = new Object();
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotTwo.unPark(vehicle));
        assertThrows(NotParkedException.class, () -> parkingLotOne.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenParkInMostAvailableCapacity_ThenAttendantShouldAbleToUnParkFromParkingLotOne() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(3);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);
        Object vehicle = new Object();
        attendant.park(vehicle);
        assertEquals(vehicle, parkingLotOne.unPark(vehicle));
        assertThrows(NotParkedException.class, () -> parkingLotTwo.unPark(vehicle));
    }

    @Test
    void givenThreeParkingLotsWithHighestCapacityFull_WhenPark_ThenAttendantAbleToParkInSecondHighestCapacity() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(3);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(1);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();

        parkingLotOne.park(vehicleOne);
        parkingLotOne.park(vehicleTwo);
        parkingLotOne.park(vehicleThree);

        attendant.park(vehicleFour);

        assertEquals(vehicleFour, parkingLotTwo.unPark(vehicleFour));


    }


    @Test
    void givenThreeParkingLotsWithNoSpaceInFirstTwo_WhenPark_ThenAttendantShouldAbleToParkInThirdHighestCapacity() throws Exception {

        ParkingLot parkingLotOne = new ParkingLot(2);
        ParkingLot parkingLotTwo = new ParkingLot(2);
        ParkingLot parkingLotThree = new ParkingLot(1);


        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();
        Object vehicleFive = new Object();

        parkingLotOne.park(vehicleOne);
        parkingLotOne.park(vehicleTwo);
        parkingLotTwo.park(vehicleThree);
        parkingLotTwo.park(vehicleFour);

        attendant.park(vehicleFive);

        assertEquals(vehicleFive, parkingLotThree.unPark(vehicleFive));
    }

    @Test
    void givenThreeParkingLotsFull_WhenPark_ThenAttendantShouldNotAbleToBePark() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        ParkingLot parkingLotTwo = new ParkingLot(1);
        ParkingLot parkingLotThree = new ParkingLot(1);


        List<ParkingLot> parkingLots = Arrays.asList(parkingLotOne, parkingLotTwo, parkingLotThree);
        MostCapacityParkingStrategy mostCapacityPark = new MostCapacityParkingStrategy(parkingLots);

        Attendant attendant = new Attendant(mostCapacityPark);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        Object vehicleThree = new Object();
        Object vehicleFour = new Object();

        parkingLotOne.park(vehicleOne);
        parkingLotTwo.park(vehicleTwo);
        parkingLotThree.park(vehicleThree);

        assertThrows(AllParkingLotsAreFullException.class, () -> attendant.park(vehicleFour));

    }

}
