package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());

    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object object = new Object();

        parkingLot.park(object);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(object));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenNoCarParked_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws NotParkedException {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();

        assertThrows(NotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenOneCarParked_WhenUnPark_ThenTheyShouldBeAbleToUnPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenOneCarParkAndUnParkAnotherCar_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);

        assertThrows(NotParkedException.class, () -> {
            parkingLot.unPark(vehicleTwo);
        });
    }

    @Test
    void givenParkedTwoVehicle_WhenUnPark_ThenTheyShouldBeUnPark() throws Exception {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        parkingLot.unPark(vehicleOne);
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));
    }


    @Test
    void givenParkingLotFull_WhenPark_ThenOwnerGetsMessage() throws VehicleAlreadyParkedException, ParkingLotFullException {
        Person owner = new Person();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.park(new Object());
        assertTrue(owner.gotInformed());
    }

    @Test
    void givenParkingLotNotFull_WhenPark_ThenOwnerGotNotInformed() throws VehicleAlreadyParkedException, ParkingLotFullException {
        Person ower = new Person();
        ParkingLot parkingLot = new ParkingLot(2, ower);
        Object vehicleOne = new Object();

        parkingLot.park(vehicleOne);

        assertFalse(ower.gotInformed());

    }
}
