package Entities;

public class Location {

    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distanceTo(Location other) {

        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(other.latitude);

        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(other.longitude);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Earth's radius in KM
        double earthRadius = 6371;

        return earthRadius * c;
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}