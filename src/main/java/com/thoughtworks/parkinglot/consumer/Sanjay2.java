package com.thoughtworks.parkinglot.consumer;

import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;

public class Sanjay2 {
    public static void park(ParkingLot one, ParkingLot two) throws ParkingLotFullException {

        Object secondCar = new Object();
        Object thirdCar = new Object();
        try {

            one.park(secondCar);
            one.park(thirdCar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
