import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    private final int id;
    private final String name;
    private final String email;

    private final FineStrategy fineStrategy;

    private final List<Loan> loans;

    public Member(int id, String name, String email, FineStrategy fineStrategy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fineStrategy = fineStrategy;
        this.loans = new ArrayList<>();
    }

    // Add a loan for this member
    public synchronized void addLoan(Loan loan) {

        if (loan == null) {
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loans.add(loan);
    }

    // Get all loans of the member
    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    // Get member ID
    public int getId() {
        return id;
    }

    // Get member name
    public String getName() {
        return name;
    }

    // Get member email
    public String getEmail() {
        return email;
    }

    // Get fine calculation strategy
    public FineStrategy getFineStrategy() {
        return fineStrategy;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}