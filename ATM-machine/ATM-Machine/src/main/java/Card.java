public class Card {
    private final String cardnumber;
    private final String accountnumber;
    private final String pin;

    public Card(String cardnumber, String accountnumber, String pin) {
        this.cardnumber = cardnumber;
        this.accountnumber = accountnumber;
        this.pin = pin;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getPin() {
        return pin;
    }
}
