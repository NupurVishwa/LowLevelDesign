import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final int transactionId;

    private final TransactionType transactionType;

    private final Member member;

    private final BookCopy bookCopy;

    private final BigDecimal amount;

    private final LocalDateTime transactionDate;

    private final String description;


    public Transaction(int transactionId, TransactionType transactionType, Member member, BookCopy bookCopy, BigDecimal amount, String description) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.member = member;
        this.bookCopy = bookCopy;
        this.amount = amount;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
    }


    public int getTransactionId() {
        return transactionId;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }


    public Member getMember() {
        return member;
    }


    public BookCopy getBookCopy() {
        return bookCopy;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {

        return "Transaction{" + "transactionId=" + transactionId + ", transactionType=" + transactionType + ", member=" + member.getName() + ", bookCopy=" + (bookCopy != null ? bookCopy.getCopyId() : "N/A") + ", amount=" + amount + ", transactionDate=" + transactionDate + ", description='" + description + '\'' + '}';
    }
}