package org.example.Classes;

abstract class Vehicle {
    String make;
    String model;
    int year;

    Vehicle(String make, String model, int year){
        this.make=make;
        this.model=model;
        this.year=year;
    }
    //abstract void updateInfo(String make, String model, int year);

    abstract public String toString();
    abstract String toFileFormat();

    abstract Vehicle fromFileFormat(String line);
}
