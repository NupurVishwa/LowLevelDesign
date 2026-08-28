import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class BankingService {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    public void createAccount(String accountNumber, double initialBalance) {

        accounts.put(accountNumber, new Account(accountNumber, initialBalance));
    }

    public void registerCard(Card card) {
        cards.put(card.getCardnumber(), card);
    }

    public boolean authenticate(Card card, String enteredPin) {

        Card storedCard = cards.get(card.getCardnumber());

        if (storedCard == null) {
            return false;
        }

        return storedCard.getPin().equals(enteredPin);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public double getBalance(String accountNumber) {

        Account account = getAccount(accountNumber);

        if (account == null) {
            throw new IllegalStateException("Account not found");
        }

        return account.getBalance();
    }

    public void processTransaction(Transaction transaction) {
        transaction.process(this);
    }
}
