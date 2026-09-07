
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcertTicketBookingSystemDemo {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       CONCERT TICKET BOOKING SYSTEM");
        System.out.println("==============================================");

        // 1. Get Booking System instance
        ConcertTicketBookingSystem bookingSystem =
                ConcertTicketBookingSystem.getInstance();


        // =====================================================
        // 2. CREATE CONCERTS
        // =====================================================

        System.out.println("\n========== CREATING CONCERTS ==========");

        Concert concert1 = createConcert1();
        Concert concert2 = createConcert2();

        bookingSystem.addConcert(concert1);
        bookingSystem.addConcert(concert2);


        // =====================================================
        // 3. DISPLAY CONCERT DETAILS
        // =====================================================

        System.out.println("\n========== CONCERT DETAILS ==========");

        concert1.displayConcertDetails();
        concert2.displayConcertDetails();


        // =====================================================
        // 4. CREATE USERS
        // =====================================================

        System.out.println("\n========== CREATING USERS ==========");

        User user1 = new User(
                "U001",
                "John Doe",
                "john@example.com"
        );

        User user2 = new User(
                "U002",
                "Jane Smith",
                "jane@example.com"
        );

        System.out.println("User created: " + user1.getName());
        System.out.println("User created: " + user2.getName());


        // =====================================================
        // 5. SEARCH CONCERT
        // =====================================================

        System.out.println("\n========== SEARCH CONCERTS ==========");

        List<Concert> searchResults =
                bookingSystem.searchConcerts(
                        "Arijit Singh",
                        "Narendra Modi Stadium",
                        concert1.getDateTime()
                );

        if (searchResults.isEmpty()) {

            System.out.println("No concerts found.");

        } else {

            for (Concert concert : searchResults) {

                System.out.println("\n-----------------------------");

                System.out.println(
                        "Concert ID: " +
                                concert.getId()
                );

                System.out.println(
                        "Concert Name: " +
                                concert.getConcertName()
                );

                System.out.println(
                        "Artist: " +
                                concert.getArtist()
                );

                System.out.println(
                        "Description: " +
                                concert.getDescription()
                );

                System.out.println(
                        "Venue: " +
                                concert.getVenue()
                );

                System.out.println(
                        "City: " +
                                concert.getCity()
                );

                System.out.println(
                        "Date & Time: " +
                                concert.getDateTime()
                );

                System.out.println(
                        "Duration: " +
                                concert.getDurationInMinutes() +
                                " minutes"
                );
            }
        }


        // =====================================================
        // 6. SHOW AVAILABLE SEATS
        // =====================================================

        System.out.println(
                "\n========== AVAILABLE SEATS =========="
        );

        showAvailableSeats(concert1);


        // =====================================================
        // 7. USER 1 BOOKS TICKETS
        // =====================================================

        System.out.println(
                "\n========== BOOKING FOR USER 1 =========="
        );

        List<Seat> selectedSeats1 =
                selectSeats(concert1, 3);

        Booking booking1 =
                bookingSystem.bookTickets(
                        user1,
                        concert1,
                        selectedSeats1
                );

        System.out.println(
                "\nBooking ID: " +
                        booking1.getId()
        );

        System.out.println(
                "User: " +
                        booking1.getUser().getName()
        );

        System.out.println(
                "Seats booked: " +
                        booking1.getSeats().size()
        );

        System.out.println(
                "Total amount: $" +
                        booking1.getTotalPrice()
        );


        // =====================================================
        // 8. CHECK SEAT AVAILABILITY AFTER BOOKING
        // =====================================================

        System.out.println(
                "\n========== SEATS AFTER BOOKING =========="
        );

        showAvailableSeats(concert1);


        // =====================================================
        // 9. USER 2 BOOKS TICKETS FOR ANOTHER CONCERT
        // =====================================================

        System.out.println(
                "\n========== BOOKING FOR USER 2 =========="
        );

        List<Seat> selectedSeats2 =
                selectSeats(concert2, 2);

        Booking booking2 =
                bookingSystem.bookTickets(
                        user2,
                        concert2,
                        selectedSeats2
                );

        System.out.println(
                "\nBooking ID: " +
                        booking2.getId()
        );

        System.out.println(
                "User: " +
                        booking2.getUser().getName()
        );

        System.out.println(
                "Seats booked: " +
                        booking2.getSeats().size()
        );

        System.out.println(
                "Total amount: $" +
                        booking2.getTotalPrice()
        );


        // =====================================================
        // 10. CANCEL FIRST BOOKING
        // =====================================================

        System.out.println(
                "\n========== CANCELLING BOOKING =========="
        );

        System.out.println(
                "Cancelling Booking ID: " +
                        booking1.getId()
        );

        bookingSystem.cancelBooking(
                booking1.getId()
        );


        // =====================================================
        // 11. VERIFY SEATS ARE AVAILABLE AGAIN
        // =====================================================

        System.out.println(
                "\n========== SEATS AFTER CANCELLATION =========="
        );

        showAvailableSeats(concert1);


        // =====================================================
        // 12. USER 2 BOOKS AGAIN
        // =====================================================

        System.out.println(
                "\n========== NEW BOOKING =========="
        );

        List<Seat> selectedSeats3 =
                selectSeats(concert1, 2);

        Booking booking3 =
                bookingSystem.bookTickets(
                        user2,
                        concert1,
                        selectedSeats3
                );

        System.out.println(
                "\nNew Booking ID: " +
                        booking3.getId()
        );

        System.out.println(
                "User: " +
                        booking3.getUser().getName()
        );

        System.out.println(
                "Seats booked: " +
                        booking3.getSeats().size()
        );

        System.out.println(
                "Total amount: $" +
                        booking3.getTotalPrice()
        );


        // =====================================================
        // 13. FINAL SEAT STATUS
        // =====================================================

        System.out.println(
                "\n========== FINAL SEAT STATUS =========="
        );

        showAvailableSeats(concert1);


        // =====================================================
        // 14. FINAL SUMMARY
        // =====================================================

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "              BOOKING SUMMARY"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Cancelled Booking : " +
                        booking1.getId()
        );

        System.out.println(
                "Active Booking 1  : " +
                        booking2.getId()
        );

        System.out.println(
                "Active Booking 2  : " +
                        booking3.getId()
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "       CONCERT BOOKING DEMO COMPLETED"
        );

        System.out.println(
                "=============================================="
        );
    }


    // =========================================================
    // CREATE CONCERT 1
    // =========================================================

    private static Concert createConcert1() {

        List<Seat> seats =
                generateSeats(100);

        return new Concert(
                "C001",
                "Arijit Singh Live",
                "Arijit Singh",
                "Live music concert featuring Arijit Singh's popular songs.",
                "Narendra Modi Stadium",
                "Ahmedabad",
                LocalDateTime.now().plusDays(30),
                180,
                seats
        );
    }


    // =========================================================
    // CREATE CONCERT 2
    // =========================================================

    private static Concert createConcert2() {

        List<Seat> seats =
                generateSeats(50);

        return new Concert(
                "C002",
                "Shreya Ghoshal Live",
                "Shreya Ghoshal",
                "A live musical evening with Shreya Ghoshal.",
                "GIFT City Arena",
                "Gandhinagar",
                LocalDateTime.now().plusDays(60),
                150,
                seats
        );
    }


    // =========================================================
    // GENERATE SEATS
    // =========================================================

    private static List<Seat> generateSeats(
            int numberOfSeats) {

        List<Seat> seats =
                new ArrayList<>();

        for (int i = 1;
             i <= numberOfSeats;
             i++) {

            String seatNumber =
                    "S" + i;

            SeatType seatType;

            double price;

            if (i <= 10) {

                seatType = SeatType.VIP;
                price = 100.0;

            } else if (i <= 30) {

                seatType = SeatType.PREMIUM;
                price = 75.0;

            } else {

                seatType = SeatType.REGULAR;
                price = 50.0;
            }


            seats.add(
                    new Seat(
                            seatNumber,
                            seatNumber,
                            seatType,
                            price
                    )
            );
        }

        return seats;
    }


    // =========================================================
    // SELECT AVAILABLE SEATS
    // =========================================================

    private static List<Seat> selectSeats(
            Concert concert,
            int numberOfSeats) {

        List<Seat> selectedSeats =
                new ArrayList<>();

        for (Seat seat :
                concert.getSeats()) {

            if (seat.getStatus() ==
                    SeatStatus.AVAILABLE) {

                selectedSeats.add(seat);

                if (selectedSeats.size() ==
                        numberOfSeats) {

                    break;
                }
            }
        }

        if (selectedSeats.size() <
                numberOfSeats) {

            throw new IllegalStateException(
                    "Not enough seats available for concert: "
                            + concert.getConcertName()
            );
        }

        return selectedSeats;
    }


    // =========================================================
    // DISPLAY AVAILABLE SEATS
    // =========================================================

    private static void showAvailableSeats(
            Concert concert) {

        long availableCount =
                concert.getSeats()
                        .stream()
                        .filter(
                                seat ->
                                        seat.getStatus() ==
                                                SeatStatus.AVAILABLE
                        )
                        .count();


        System.out.println(
                "Concert: " +
                        concert.getConcertName()
        );

        System.out.println(
                "Available Seats: " +
                        availableCount +
                        "/" +
                        concert.getSeats().size()
        );


        System.out.println(
                "Available Seat Numbers:"
        );

        concert.getSeats()
                .stream()
                .filter(
                        seat ->
                                seat.getStatus() ==
                                        SeatStatus.AVAILABLE
                )
                .limit(15)
                .forEach(
                        seat ->
                                System.out.println(
                                        "  " +
                                                seat.getSeatNumber() +
                                                " | " +
                                                seat.getSeatType() +
                                                " | $" +
                                                seat.getPrice()
                                )
                );

        if (availableCount > 15) {

            System.out.println(
                    "  ... and " +
                            (availableCount - 15) +
                            " more seats"
            );
        }
    }
}
