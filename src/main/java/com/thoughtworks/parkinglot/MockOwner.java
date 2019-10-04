package com.thoughtworks.parkinglot;

public class MockOwner extends Owner {
    public boolean isReceived = false;

    public int count=0;
    @Override
    public void inform(String message) {
        isReceived = true;
        count++;
    }
}
