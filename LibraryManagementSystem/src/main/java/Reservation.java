import java.time.LocalDateTime;

public class Reservation {

    private final int id;

    private final Member member;

    private final Book book;

    private final LocalDateTime reservationDate;

    private boolean fulfilled;


    public Reservation(int id, Member member, Book book) {

        this.id = id;
        this.member = member;
        this.book = book;

        this.reservationDate = LocalDateTime.now();

        this.fulfilled = false;
    }


    public void fulfill() {

        fulfilled = true;
    }


    public int getId() {
        return id;
    }


    public Member getMember() {
        return member;
    }


    public Book getBook() {
        return book;
    }


    public LocalDateTime getReservationDate() {
        return reservationDate;
    }


    public boolean isFulfilled() {
        return fulfilled;
    }


    @Override
    public String toString() {

        return "Reservation{" + "id=" + id + ", member=" + member.getName() + ", book=" + book.getTitle() + ", fulfilled=" + fulfilled + '}';
    }
}