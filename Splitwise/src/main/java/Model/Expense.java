package Model;
import Enum.SplitType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Expense {

    private final String id;
    private final String description;
    private final BigDecimal amount;
    private final User paidBy;
    private final List<User> paidFor;
    private final SplitType splitType;
    private final List<Split> splits;

    public Expense(
            String id,
            String description,
            BigDecimal amount,
            User paidBy,
            List<User> paidFor,
            SplitType splitType) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.paidFor = new ArrayList<>(paidFor);
        this.splitType = splitType;
        this.splits = new ArrayList<>();
    }

    public void setSplits(List<Split> calculatedSplits) {
        splits.clear();
        splits.addAll(calculatedSplits);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getPaidFor() {
        return Collections.unmodifiableList(paidFor);
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public List<Split> getSplits() {
        return Collections.unmodifiableList(splits);
    }
}