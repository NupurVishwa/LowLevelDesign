class Message {

    private int id;

    private User sender;

    private User receiver;

    private String content;


    public Message(
            int id,
            User sender,
            User receiver,
            String content) {

        this.id = id;

        this.sender = sender;

        this.receiver = receiver;

        this.content = content;
    }


    public User getSender() {

        return sender;
    }


    public User getReceiver() {

        return receiver;
    }


    public String getContent() {

        return content;
    }
}