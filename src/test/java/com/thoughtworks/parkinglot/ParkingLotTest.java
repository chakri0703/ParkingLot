package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenMustPark() throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Object vehicle =new Object();
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
    void givenParkedOneCar_WhenUnPark_ThenTheyShouldBeAbleToUnPark(){
        ParkingLot parkingLot=new ParkingLot(1);

        Object vehicle=new Object();
        assertEquals(false,parkingLot.unPark(vehicle));
    }


}
