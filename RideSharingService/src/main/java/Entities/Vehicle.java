package Entities;

import Enums.RideType;

public class Vehicle {

    private final String vehicleId;
    private final String vehicleNumber;
    private final RideType rideType;

    public Vehicle(String vehicleId,
                   String vehicleNumber,
                   RideType rideType) {

        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.rideType = rideType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public RideType getRideType() {
        return rideType;
    }

    @Override
    public String toString() {
        return rideType + " - " + vehicleNumber;
    }
}