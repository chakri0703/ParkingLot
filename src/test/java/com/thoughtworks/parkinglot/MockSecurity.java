package com.thoughtworks.parkinglot;

public class MockSecurity implements Subscriber {
    int parkingFullInform = 0;
    int freeSpaceInform = 0;

    @Override
    public void informParkingLotFull() {
        parkingFullInform++;
    }

    @Override
    public void informFreeSpaceAvailable() {
        freeSpaceInform++;
    }
}
