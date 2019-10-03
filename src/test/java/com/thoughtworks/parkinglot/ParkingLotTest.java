package com.thoughtworks.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest
{
    @Test
    void givenParkingLot_WhenIsAvailable_ThenMustPark(){
        ParkingLot parkingLot=new ParkingLot(10);

        Assertions.assertEquals(true,parkingLot.park(new Object()));

    }
}
