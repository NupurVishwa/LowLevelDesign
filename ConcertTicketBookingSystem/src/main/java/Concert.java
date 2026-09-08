import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concert {

    private final String id;
    private final String concertName;
    private final String artist;
    private final String description;
    private final String venue;
    private final String city;
    private final LocalDateTime dateTime;
    private final int durationInMinutes;

    private final List<Seat> seats;


    public Concert(String id, String concertName, String artist, String description, String venue, String city, LocalDateTime dateTime, int durationInMinutes, List<Seat> seats) {

        this.id = id;
        this.concertName = concertName;
        this.artist = artist;
        this.description = description;
        this.venue = venue;
        this.city = city;
        this.dateTime = dateTime;
        this.durationInMinutes = durationInMinutes;

        this.seats = new ArrayList<>(seats);
    }


    public String getId() {
        return id;
    }

    public String getConcertName() {
        return concertName;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }


    public void displayConcertDetails() {

        System.out.println("\n===== CONCERT DETAILS =====");

        System.out.println("Concert ID: " + id);
        System.out.println("Concert Name: " + concertName);
        System.out.println("Artist: " + artist);
        System.out.println("Description: " + description);
        System.out.println("Venue: " + venue);
        System.out.println("City: " + city);
        System.out.println("Date & Time: " + dateTime);
        System.out.println("Duration: " + durationInMinutes + " minutes");
        System.out.println("Total Seats: " + seats.size());
    }


    @Override
    public String toString() {

        return "Concert{" + "id='" + id + '\'' + ", concertName='" + concertName + '\'' +
                ", artist='" + artist + '\'' +
                ", venue='" + venue + '\'' +
                ", city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}

