package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());

    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");

    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object object = new Object();

        parkingLot.park(object);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(object));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenNoCarParked_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws NotParkedException {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);

        Object vehicle = new Object();

        assertThrows(NotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenOneCarParked_WhenUnPark_ThenTheyShouldBeAbleToUnPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenOneCarParkAndUnParkAnotherCar_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws Exception {
        MockOwner owner = new MockOwner();
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
        MockOwner owner = new MockOwner();
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
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.park(new Object());
        assertEquals(1, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenPark_ThenOwnerGetsMessageOnce() throws VehicleAlreadyParkedException, ParkingLotFullException {
        MockOwner owner = new MockOwner();

        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.park(new Object());
        assertEquals(1, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenParkAndUnParkAgainPark_ThenOwerGetsMessageTwice() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        MockOwner owner = new MockOwner();

        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object one = new Object();
        parkingLot.park(one);
        parkingLot.unPark(one);
        parkingLot.park(one);
        assertEquals(2, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenUnPark_ThenOwnerGetsMessage() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        MockOwner owner = new MockOwner();

        ParkingLot parkingLot = new ParkingLot(1, owner);
        Object vehicle = new Object();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        assertEquals(1, owner.freeSpaceInform);
    }
}
