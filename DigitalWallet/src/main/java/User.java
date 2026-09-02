import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    private List<Account> accounts;

    public User(String name, String email, String phoneNumber) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.accounts = new ArrayList<>();
    }

    public void updateProfile(
            String name,
            String email,
            String phoneNumber
    ) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DigitalWallet getWallet() {
        return DigitalWallet.getWallet(this);
    }
}