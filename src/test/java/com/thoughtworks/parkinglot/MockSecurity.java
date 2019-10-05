package com.thoughtworks.parkinglot;

public class MockSecurity implements Subscriber {
    public int parkingFullInform = 0;
    public int freeSpaceInform = 0;

    @Override
    public void informParkingLotFull() {
        parkingFullInform++;
    }

    @Override
    public void informFreeSpaceAvailable() {
        freeSpaceInform++;
    }
}
