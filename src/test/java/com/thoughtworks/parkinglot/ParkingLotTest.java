package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;
import org.mockito.internal.configuration.injection.filter.OngoingInjecter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());

    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");

    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.register(owner);

        Object object = new Object();

        parkingLot.park(object);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(object));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenNoCarParked_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws NotParkedException {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);

        Object vehicle = new Object();

        assertThrows(NotParkedException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenOneCarParked_WhenUnPark_ThenTheyShouldBeAbleToUnPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenOneCarParkAndUnParkAnotherCar_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws Exception {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
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
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.register(owner);

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
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.park(new Object());
        assertEquals(1, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenPark_ThenOwnerGetsMessageOnce() throws VehicleAlreadyParkedException, ParkingLotFullException {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.park(new Object());
        assertEquals(1, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenParkAndUnParkAgainPark_ThenOwnerGetsMessageTwice() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        MockOwner owner = new MockOwner();

        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        Object one = new Object();
        parkingLot.park(one);
        parkingLot.unPark(one);
        parkingLot.park(one);
        assertEquals(2, owner.parkingFullInform);
    }

    @Test
    void givenParkingLotFull_WhenUnPark_ThenOwnerGetsMessage() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        MockOwner owner = new MockOwner();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        Object vehicle = new Object();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        assertEquals(1, owner.freeSpaceInform);
    }

    @Test
    void givenParkingLotFull_WhenPark_ThenOwnerAndSecurityGetsMessage() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        MockOwner owner = new MockOwner();
        MockSecurity security = new MockSecurity();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.register(security);
        Object vehicle = new Object();
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertEquals(1, owner.parkingFullInform);
        assertEquals(1, owner.freeSpaceInform);
        assertEquals(1, security.parkingFullInform);
        assertEquals(1, security.freeSpaceInform);
    }

    @Test
    void givenParkingLotFull_WhenParkAndUnPark_ThenOwnerAndSecurityGetsMessage() throws Exception {
        MockOwner owner = new MockOwner();
        MockSecurity security = new MockSecurity();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.register(security);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertEquals(2, owner.parkingFullInform);
        assertEquals(2, owner.freeSpaceInform);
        assertEquals(2, security.parkingFullInform);
        assertEquals(2, security.freeSpaceInform);
    }

    @Test
    void givenParkingLotAndAddingNewPerson_WhenParkAndUnPark_ThenNewPersonAlsoGetsMessage() throws Exception {
        MockSecurity security = new MockSecurity();
        MockOwner owner = new MockOwner();
        Object vehicle = new Object();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.register(security);
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        MockSecurity securityTwo = new MockSecurity();
        parkingLot.register(securityTwo);
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertEquals(2, owner.parkingFullInform);
        assertEquals(2, owner.freeSpaceInform);
        assertEquals(2, security.parkingFullInform);
        assertEquals(2, security.freeSpaceInform);
        assertEquals(1, securityTwo.freeSpaceInform);
        assertEquals(1, securityTwo.parkingFullInform);
    }

    @Test
    void givenParkingLotRemovingOnePerson_WhenParkAndUnPark_ThenRemovedPersonShouldNotGetMessages() throws Exception {
        MockSecurity security = new MockSecurity();
        MockOwner owner = new MockOwner();
        Object vehicle = new Object();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.register(owner);
        parkingLot.register(security);
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        parkingLot.unRegister(security);

        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);

        assertEquals(2, owner.parkingFullInform);
        assertEquals(2, owner.freeSpaceInform);
        assertEquals(1, security.parkingFullInform);
        assertEquals(1, security.freeSpaceInform);
    }
}
