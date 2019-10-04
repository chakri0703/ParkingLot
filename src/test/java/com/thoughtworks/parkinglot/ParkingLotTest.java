package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertTrue(parkingLot.isParkingLotFull());

    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        parkingLot.park(new Object());

        Throwable exception = assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Object()));
        assertEquals(exception.getMessage(), "parking Lot is Full");
    }

    @Test
    void givenParkingLotWithSameCars_WhenPark_ThenMustNotPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object object = new Object();

        parkingLot.park(object);

        Throwable exception = assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(object));
        assertEquals(exception.getMessage(), "vehicle already parked");
    }

    @Test
    void givenNoCarParked_WhenUnPark_ThenTheyShouldNotBeAbleToUnPark() throws NotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle = new Object();

        assertThrows(NotParkedException.class,()->{
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenOneCarParked_WhenUnPark_ThenTheyShouldBeAbleToUnPark() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle, parkingLot.unPark(vehicle));
    }

    @Test
    void givenOneCarParkAndUnParkAnotherCar_WhenUnPark_ThenTheyShouldNotAbleToUnPark() throws VehicleAlreadyParkedException, ParkingLotFullException, NotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);

       assertThrows(NotParkedException.class,()->{
           parkingLot.unPark(vehicleTwo);
       });
    }

    @Test
    void givenParkedTwoVehicle_WhenUnPark_ThenTheyShouldBeUnPark() throws Exception {
        ParkingLot parkingLot=new ParkingLot(2);
        Object vehicleOne=new Object();
        Object vehicleTwo=new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

       parkingLot.unPark(vehicleOne);
       assertEquals(vehicleTwo,parkingLot.unPark(vehicleTwo));
    }

}
