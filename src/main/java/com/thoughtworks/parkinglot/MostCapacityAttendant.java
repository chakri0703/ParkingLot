package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.AllParkingLotsAreFullException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.Collections;
import java.util.List;

public class MostCapacityAttendant extends Attendant {

    private List<ParkingLot> parkingLots;

    public MostCapacityAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public void park(Object vehicle) throws VehicleAlreadyParkedException, AllParkingLotsAreFullException {
        parkingLots.sort(Collections.reverseOrder());
        boolean wasAbleToPark = false;
        for (ParkingLot parkingLot : parkingLots) {
            try {
                parkingLot.park(vehicle);
                wasAbleToPark = true;
            } catch (ParkingLotFullException e) {
                e.printStackTrace();
            }
            if (wasAbleToPark) {
                return;
            }
        }
        throw new AllParkingLotsAreFullException("All parkingLots full");
    }
}
