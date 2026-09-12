package Entities;

import Enums.DriverStatus;

public class Driver {

    private final String id;
    private final String name;

    private Location currentLocation;
    private Vehicle vehicle;
    private DriverStatus status;

    public Driver(String id,
                  String name,
                  Location currentLocation,
                  Vehicle vehicle) {

        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.vehicle = vehicle;
        this.status = DriverStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}