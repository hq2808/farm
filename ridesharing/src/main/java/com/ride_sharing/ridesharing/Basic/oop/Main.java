package com.ride_sharing.ridesharing.Basic.oop;

interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike"); }
}

//Truck, Bus.
class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Truck"); }
}

class Bus implements Vehicle {
    @Override
    public void drive() {
        System.out.println("On the bus"); }
}


class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        return switch (type) {
            case "Car" -> new Car();
            case "Bike" -> new Bike();
            case "Truck" -> new Truck();
            case "Bus" -> new Bus();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle("Car");
        vehicle.drive();
        vehicle = VehicleFactory.getVehicle("Bike");
        vehicle.drive();
        vehicle = VehicleFactory.getVehicle("Truck");
        vehicle.drive();
        vehicle = VehicleFactory.getVehicle("Bus");
        vehicle.drive();

        Singleton singleton = Singleton.getInstance();
        System.out.printf("Hello");
    }
}