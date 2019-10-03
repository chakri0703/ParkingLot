package com.thoughtworks.parkinglot.consumer;

import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;

public class Sanjay {

    public static void park(ParkingLot one, ParkingLot two) throws ParkingLotFullException {

        Object firsCar = new Object();

        try {
            one.park(firsCar);
            one.park(firsCar);
        } catch (ParkingLotFullException parkinglotExceptions) {
            System.out.println(parkinglotExceptions.getMessage());
        }

    }

    public static void main(String[] args) throws ParkingLotFullException {
        ParkingLot one = new ParkingLot(2);
        ParkingLot two = new ParkingLot(3);

        Sanjay.park(one, two);
        Sanjay2.park(one,two);
    }
}
