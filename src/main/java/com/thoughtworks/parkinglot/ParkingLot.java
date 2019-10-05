package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {

    private int capacity;
    private Set<Object> vehicles = new HashSet<>();
    private List<Subscriber> persons = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }


    public void park(Object object) throws ParkingLotFullException, VehicleAlreadyParkedException {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException("parking Lot is Full");
        }

        if (isParked(object)) {
            throw new VehicleAlreadyParkedException("vehicle already parked");
        }
        vehicles.add(object);
        if (vehicles.size() == capacity) {
            for (Subscriber person : persons) {
                person.informParkingLotFull();
            }
        }
    }

    private boolean isParked(Object object) {
        return vehicles.contains(object);
    }

    protected boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }

    public Object unPark(Object vehicle) throws NotParkedException {
        if (!isParked(vehicle) || vehicles.isEmpty()) {
            throw new NotParkedException("Vehicle Not parked");

        }
        if (vehicles.size() == capacity) {
            vehicles.remove(vehicle);
            for (Subscriber person : persons) {
                person.informFreeSpaceAvailable();
            }
        }
        vehicles.remove(vehicle);
        return vehicle;
    }

    int getCapacity() {
        return this.capacity;
    }

    public void register(Subscriber person) {
        persons.add(person);
    }

    public void unRegister(Subscriber person) {
        persons.remove(person);
    }
}
