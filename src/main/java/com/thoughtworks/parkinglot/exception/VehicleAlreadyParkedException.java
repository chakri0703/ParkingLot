package com.thoughtworks.parkinglot.exception;

public class VehicleAlreadyParkedException extends Exception {

    public VehicleAlreadyParkedException(String message) {
        super(message);
    }
}
