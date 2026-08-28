import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;

    private Profile profile;

    private List<User> connections;

    private List<Message> messages;

    private List<Notification> notifications;


    public User(int id, String name) {

        this.id = id;
        this.name = name;

        this.profile = new Profile();

        this.connections = new ArrayList<>();

        this.messages = new ArrayList<>();

        this.notifications = new ArrayList<>();
    }


    // =====================================================
    // GETTERS
    // =====================================================

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Profile getProfile() {
        return profile;
    }


    public List<User> getConnections() {
        return connections;
    }


    public List<Message> getMessages() {
        return messages;
    }


    public List<Notification> getNotifications() {
        return notifications;
    }


    // =====================================================
    // PROFILE METHODS
    // =====================================================

    public void addSkill(Skill skill) {

        profile.addSkill(skill);
    }


    public void addEducation(Education education) {

        profile.addEducation(education);
    }


    public void addExperience(Experience experience) {

        profile.addExperience(experience);
    }


    // =====================================================
    // CONNECTION METHODS
    // =====================================================

    public void addConnection(User user) {

        if (!connections.contains(user)) {

            connections.add(user);
        }
    }


    public boolean isConnectedTo(User user) {

        return connections.contains(user);
    }


    // =====================================================
    // MESSAGE METHODS
    // =====================================================

    public void addMessage(Message message) {

        messages.add(message);
    }


    // =====================================================
    // NOTIFICATION METHODS
    // =====================================================

    public void addNotification(
            Notification notification) {

        notifications.add(notification);
    }


    public void displayNotifications() {

        System.out.println(
                "\nNotifications for " + name + ":"
        );

        if (notifications.isEmpty()) {

            System.out.println("No notifications");

            return;
        }

        for (Notification notification :
                notifications) {

            System.out.println(
                    notification.getMessage()
            );
        }
    }


    // =====================================================
    // MESSAGE DISPLAY
    // =====================================================

    public void displayMessages() {

        System.out.println(
                "\nMessages for " + name + ":"
        );

        if (messages.isEmpty()) {

            System.out.println("No messages");

            return;
        }

        for (Message message : messages) {

            System.out.println(
                    message.getSender().getName()
                            + ": "
                            + message.getContent()
            );
        }
    }
}