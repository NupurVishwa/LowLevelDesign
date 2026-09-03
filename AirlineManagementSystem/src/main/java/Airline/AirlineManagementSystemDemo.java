package Airline;

import Booking.Booking;
import Booking.BookingManager;
import Flight.Flight;
import Flight.FlightSearch;
import Payment.PaymentProcessor;
import Seat.Seat;
import Seat.SeatType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AirlineManagementSystemDemo {

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        // Create Airline Management System
        AirlineManagementSystem airlineManagementSystem =
                new AirlineManagementSystem();

        // =========================
        // Create Passengers
        // =========================
        Passenger passenger1 =
                airlineManagementSystem.addPassenger(
                        "John Doe",
                        "john@example.com"
                );

        Passenger passenger2 =
                airlineManagementSystem.addPassenger(
                        "John Smith",
                        "smith@example.com"
                );


        // =========================
        // Create Aircrafts
        // =========================
        Aircraft aircraft1 =
                airlineManagementSystem.addAircraft(
                        "A001",
                        "Boeing 747",
                        300
                );

        Aircraft aircraft2 =
                airlineManagementSystem.addAircraft(
                        "A002",
                        "Airbus A380",
                        500
                );


        // =========================
        // Create Flights
        // =========================
        LocalDateTime departureTime1 =
                LocalDateTime.now().plusDays(1);

        LocalDateTime arrivalTime1 =
                departureTime1.plusHours(2);

        Flight flight1 =
                airlineManagementSystem.addFlight(
                        "New York",
                        "London",
                        departureTime1,
                        arrivalTime1,
                        aircraft1.getTailNumber()
                );


        LocalDateTime departureTime2 =
                LocalDateTime.now().plusDays(3);

        LocalDateTime arrivalTime2 =
                departureTime2.plusHours(5);

        Flight flight2 =
                airlineManagementSystem.addFlight(
                        "Paris",
                        "Tokyo",
                        departureTime2,
                        arrivalTime2,
                        aircraft2.getTailNumber()
                );


        // =========================
        // Search Flights
        // =========================
        List<Flight> searchResults =
                airlineManagementSystem.searchFlights(
                        "New York",
                        "London",
                        LocalDate.now().plusDays(1)
                );

        System.out.println("\nSearch Results:");

        for (Flight flight : searchResults) {
            System.out.println(
                    "Flight: "
                            + flight.getFlightNumber()
                            + " - "
                            + flight.getSource()
                            + " to "
                            + flight.getDestination()
            );
        }


        // =========================
        // Book a Flight
        // =========================
        Booking booking =
                airlineManagementSystem.bookFlight(
                        flight1.getFlightNumber(),
                        passenger1.getId(),
                        new Seat("25A", SeatType.ECONOMY),
                        100
                );

        if (booking != null) {
            System.out.println(
                    "\nBooking successful. Booking ID: "
                            + booking.getId()
            );
        } else {
            System.out.println("\nBooking failed.");
        }


        // =========================
        // Cancel Booking
        // =========================
        if (booking != null) {
            airlineManagementSystem.cancelBooking(
                    booking.getId()
            );

            System.out.println("Booking cancelled.");
        }
    }
}