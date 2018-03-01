package utils;

import domain.Point;

public class DistanceCalculator {
    private static final int EARTH_RADIUS_KM = 6371;

    public static Long calculate(Point p1, Point p2) {
        double dLat = Math.toRadians(p2.getLatitude()) - Math.toRadians(p1.getLatitude());
        double dLon = Math.toRadians(p2.getLongitude()) - Math.toRadians(p1.getLongitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(Math.toRadians(p1.getLatitude())) * Math.cos(Math.toRadians(p2.getLatitude()));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round(EARTH_RADIUS_KM * c);
    }
}