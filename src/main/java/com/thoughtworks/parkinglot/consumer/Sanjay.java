package com.thoughtworks.parkinglot.consumer;

import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

public class Sanjay {
    public static void park(ParkingLot one, ParkingLot two) throws VehicleAlreadyParkedException, ParkingLotFullException {
        Object firstCar = new Object();
        Object secondCar = new Object();
        Object thirdCar = new Object();
        Object fourthCar = new Object();
        Object fifthCar = new Object();

        Object vehicle[] = {firstCar, firstCar,secondCar, thirdCar,firstCar, fourthCar, fifthCar};
        for (int i = 0; i < vehicle.length; i++) {

            try {
                one.park(vehicle[i]);
                System.out.println(vehicle[i] + " just parked");
            } catch (VehicleAlreadyParkedException e) {
                System.out.println(vehicle[i] + " is already parked in lot 1");
            } catch (ParkingLotFullException ex) {
                try {
                    two.park(vehicle[i]);
                    System.out.println(vehicle[i]+" is just parked");
                } catch (VehicleAlreadyParkedException parked) {
                    System.out.println(vehicle[i] + " is already parked in lot 2");
                } catch (ParkingLotFullException exc) {
                    System.out.println("Both Parking lots are Full");
                }
            }

        }
    }

    public static void main(String[] args) throws ParkingLotFullException, VehicleAlreadyParkedException {
        ParkingLot one = new ParkingLot(2);
        ParkingLot two = new ParkingLot(3);

        Sanjay.park(one, two);
    }
}
