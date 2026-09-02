import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private String id;
    private Account from;
    private Account to;
    private BigDecimal amount;
    private Currency currency;
    private TransactionStatus status;
    private TransactionType type;
    private LocalDateTime timestamp;

    public Transaction(
            Account from,
            Account to,
            BigDecimal amount,
            Currency currency
    ) {

        this.id = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.currency = currency;

        this.status = TransactionStatus.PENDING;
        this.type = TransactionType.TRANSFER;
        this.timestamp = LocalDateTime.now();
    }

    public void process() {

        if (status != TransactionStatus.PENDING) {
            return;
        }

        try {

            from.withdraw(amount);
            to.deposit(amount);

            status = TransactionStatus.COMPLETED;

        } catch (Exception e) {

            status = TransactionStatus.FAILED;
            throw e;
        }
    }

    public void cancel() {

        if (status == TransactionStatus.PENDING) {
            status = TransactionStatus.CANCELLED;
        }
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}