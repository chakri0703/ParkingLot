package com.thoughtworks.parkinglot.exception;

public class AllParkingLotsAreFullException extends Exception {
    public AllParkingLotsAreFullException(String all_parkingLots_full) {
        super(all_parkingLots_full);
    }
}
