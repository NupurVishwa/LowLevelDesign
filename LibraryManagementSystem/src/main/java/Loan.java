import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {

    private final int id;

    private final Member member;

    private final BookCopy bookCopy;

    private final LocalDate loanDate;

    private final LocalDate dueDate;

    private LocalDate returnDate;


    public Loan(int id, Member member, BookCopy bookCopy, int loanDurationDays) {

        this.id = id;
        this.member = member;
        this.bookCopy = bookCopy;

        this.loanDate = LocalDate.now();

        this.dueDate = loanDate.plusDays(loanDurationDays);
    }


    public void completeLoan() {

        if (returnDate != null) {

            throw new IllegalStateException("Book already returned");
        }

        returnDate = LocalDate.now();
    }


    public boolean isReturned() {

        return returnDate != null;
    }


    public long getOverdueDays() {
        LocalDate endDate = returnDate != null ? returnDate : LocalDate.now();


        if (!endDate.isAfter(dueDate)) {
            return 0;
        }


        return ChronoUnit.DAYS.between(dueDate, endDate);
    }


    public int getId() {
        return id;
    }


    public Member getMember() {
        return member;
    }


    public BookCopy getBookCopy() {
        return bookCopy;
    }


    public LocalDate getLoanDate() {
        return loanDate;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }


    public LocalDate getReturnDate() {
        return returnDate;
    }


    @Override
    public String toString() {

        return "Loan{" + "id=" + id + ", member=" + member.getName() + ", book=" + bookCopy.getBook().getTitle() + ", copyId=" + bookCopy.getCopyId() + ", loanDate=" + loanDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + '}';
    }
}