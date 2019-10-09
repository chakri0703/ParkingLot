package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exception.NotParkedException;
import com.thoughtworks.parkinglot.exception.ParkingLotFullException;
import com.thoughtworks.parkinglot.exception.VehicleAlreadyParkedException;

import java.util.*;

public class ParkingLot implements Comparable<ParkingLot> {

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
        if (isParkingLotFull()) {
            informLotFull();
        }
    }

    private boolean isParked(Object object) {
        return vehicles.contains(object);
    }

    protected boolean isParkingLotFull() {
        return vehicles.size() >= capacity;
    }

    public Object unPark(Object vehicle) throws NotParkedException {
        if (checkVehiclePresence(vehicle)) {
            throw new NotParkedException("Vehicle Not parked");

        }
        if (isParkingLotFull()) {
            vehicles.remove(vehicle);
            informLotAvailable();
        }
        vehicles.remove(vehicle);
        return vehicle;
    }

    public void register(Subscriber person) {
        persons.add(person);
    }

    public void unRegister(Subscriber person) {
        persons.remove(person);
    }


    private boolean checkVehiclePresence(Object vehicle){
        return !isParked(vehicle) || vehicles.isEmpty();
    }

    private void informLotFull(){
        for (Subscriber person : persons) {
            person.informParkingLotFull();
        }
    }
    private void informLotAvailable(){
        for (Subscriber person : persons) {
            person.informFreeSpaceAvailable();
        }
    }
    @Override
    public int compareTo(ParkingLot o) {
        return (capacity - o.capacity);
    }

    int getCapacity() {
        return this.capacity;
    }

    int getVehicleSize() {
        return vehicles.size();
    }
}
