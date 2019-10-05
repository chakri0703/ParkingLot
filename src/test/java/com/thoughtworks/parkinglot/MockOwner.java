package com.thoughtworks.parkinglot;

public class MockOwner extends Owner {

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
