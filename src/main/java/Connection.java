public class Connection {

    private User sender;
    private User receiver;

    private ConnectionStatus status;


    public Connection(int i, User sender, User receiver) {

        this.sender = sender;
        this.receiver = receiver;

        this.status = ConnectionStatus.PENDING;
    }


    public User getSender() {

        return sender;
    }


    public User getReceiver() {

        return receiver;
    }


    public ConnectionStatus getStatus() {

        return status;
    }


    public void accept() {

        status = ConnectionStatus.ACCEPTED;
    }


    public void reject() {

        status = ConnectionStatus.REJECTED;
    }
}
