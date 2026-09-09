package Model;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class User {

    private final String id;
    private String name;
    private String email;

    /*
     * balanceMap.get(otherUser)
     *
     * Positive:
     * otherUser owes this user
     *
     * Negative:
     * this user owes otherUser
     */
    private final Map<User, BigDecimal> balances;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balances = new HashMap<>();
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBalance(User otherUser, BigDecimal amount) {
        balances.merge(
                otherUser,
                amount,
                BigDecimal::add
        );
    }

    public void subtractBalance(User otherUser, BigDecimal amount) {
        addBalance(otherUser, amount.negate());
    }

    public BigDecimal getBalance(User otherUser) {
        return balances.getOrDefault(
                otherUser,
                BigDecimal.ZERO
        );
    }

    public Map<User, BigDecimal> getBalances() {
        return new HashMap<>(balances);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name;
    }

    /*
     * Important because User is used as Map key.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}