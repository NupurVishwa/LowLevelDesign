package Model;
import Enum.TransactionStatus;

import java.math.BigDecimal;

public class Transaction {

    private final String id;
    private final User from;
    private final User to;
    private final BigDecimal amount;

    private TransactionStatus status;

    public Transaction(
            String id,
            User from,
            User to,
            BigDecimal amount) {

        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
    }

    public void execute() {
        status = TransactionStatus.COMPLETED;
    }

    public String getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}