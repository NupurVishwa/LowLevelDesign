package Model;
import java.sql.Timestamp;

public class Reservation {

    private int id;
    private String customerName;
    private String customerPhone;
    private int numberOfGuests;
    private Timestamp reservationTime;

    public Reservation(
            int id,
            String customerName,
            String customerPhone,
            int numberOfGuests,
            Timestamp reservationTime
    ) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.numberOfGuests = numberOfGuests;
        this.reservationTime = reservationTime;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                ", reservationTime=" + reservationTime +
                '}';
    }
}
