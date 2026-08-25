import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedInService {

    private Map<Integer, User> users;

    private List<Connection> connections;

    private List<JobPosting> jobPostings;

    private NotificationService notificationService;

    private int connectionId;

    private int messageId;


    public LinkedInService() {

        users = new HashMap<>();

        connections = new ArrayList<>();

        jobPostings = new ArrayList<>();

        notificationService =
                new NotificationService();

        connectionId = 1;

        messageId = 1;
    }


    // =====================================================
    // USER MANAGEMENT
    // =====================================================

    public void registerUser(User user) {

        if (users.containsKey(user.getId())) {

            System.out.println(
                    "User already exists."
            );

            return;
        }


        users.put(
                user.getId(),
                user
        );


        // Observer Pattern
        notificationService.subscribe(
                user,
                new UserNotificationObserver(user)
        );


        System.out.println(
                "User registered: "
                        + user.getName()
        );
    }


    public User getUser(int id) {

        return users.get(id);
    }


    // =====================================================
    // CONNECTION REQUEST
    // =====================================================

    public void sendConnectionRequest(
            User sender,
            User receiver) {

        if (sender == receiver) {

            System.out.println(
                    "Cannot connect with yourself."
            );

            return;
        }


        if (sender.isConnectedTo(receiver)) {

            System.out.println(
                    "Already connected."
            );

            return;
        }


        Connection existing =
                findConnection(
                        sender,
                        receiver
                );


        if (existing != null) {

            System.out.println(
                    "Connection request already exists."
            );

            return;
        }


        Connection connection =
                new Connection(
                        connectionId++,
                        sender,
                        receiver
                );


        connections.add(connection);


        // Notify receiver
        notificationService.notifyUser(
                receiver,
                NotificationType.CONNECTION_REQUEST,
                sender.getName()
                        + " sent you a connection request."
        );
    }


    // =====================================================
    // ACCEPT CONNECTION
    // =====================================================

    public void acceptConnection(
            User sender,
            User receiver) {

        Connection connection =
                findConnection(
                        sender,
                        receiver
                );


        if (connection == null) {

            System.out.println(
                    "Connection request not found."
            );

            return;
        }


        if (connection.getStatus()
                != ConnectionStatus.PENDING) {

            System.out.println(
                    "Request is not pending."
            );

            return;
        }


        connection.accept();


        // Add both users to each other's network
        sender.addConnection(receiver);

        receiver.addConnection(sender);


        // Notify sender
        notificationService.notifyUser(
                sender,
                NotificationType.CONNECTION_ACCEPTED,
                receiver.getName()
                        + " accepted your connection request."
        );
    }


    // =====================================================
    // REJECT CONNECTION
    // =====================================================

    public void rejectConnection(
            User sender,
            User receiver) {

        Connection connection =
                findConnection(
                        sender,
                        receiver
                );


        if (connection == null) {

            System.out.println(
                    "Connection request not found."
            );

            return;
        }


        connection.reject();
    }


    // =====================================================
    // FIND CONNECTION
    // =====================================================

    private Connection findConnection(
            User sender,
            User receiver) {

        for (Connection connection :
                connections) {

            if (connection.getSender() == sender
                    && connection.getReceiver()
                    == receiver) {

                return connection;
            }
        }

        return null;
    }


    // =====================================================
    // MESSAGING
    // =====================================================

    public void sendMessage(
            User sender,
            User receiver,
            String content) {


        // Only connections can message
        if (!sender.isConnectedTo(receiver)) {

            System.out.println(
                    "Users are not connected."
            );

            return;
        }


        Message message =
                new Message(
                        messageId++,
                        sender,
                        receiver,
                        content
                );


        // Store message
        sender.addMessage(message);

        receiver.addMessage(message);


        // Notify receiver
        notificationService.notifyUser(
                receiver,
                NotificationType.MESSAGE,
                "New message from "
                        + sender.getName()
        );
    }


    // =====================================================
    // JOB POSTING
    // =====================================================

    public void postJob(JobPosting job) {

        jobPostings.add(job);

        System.out.println(
                "Job posted: "
                        + job.getTitle()
        );
    }


    // =====================================================
    // JOB SEARCH
    // =====================================================

    public List<JobPosting> searchJobs(
            String keyword) {

        List<JobPosting> result =
                new ArrayList<>();


        for (JobPosting job :
                jobPostings) {

            if (job.getTitle()
                    .toLowerCase()
                    .contains(
                            keyword.toLowerCase()
                    )

                    ||

                    job.getDescription()
                            .toLowerCase()
                            .contains(
                                    keyword.toLowerCase()
                            )) {

                result.add(job);
            }
        }


        return result;
    }


    // =====================================================
    // USER SEARCH
    // =====================================================

    public List<User> searchUsers(
            String keyword) {

        List<User> result =
                new ArrayList<>();


        for (User user :
                users.values()) {

            if (user.getName()
                    .toLowerCase()
                    .contains(
                            keyword.toLowerCase()
                    )) {

                result.add(user);
            }
        }


        return result;
    }


    // =====================================================
    // GET JOBS
    // =====================================================

    public List<JobPosting> getJobPostings() {

        return jobPostings;
    }


    // =====================================================
    // GET CONNECTIONS
    // =====================================================

    public List<Connection> getConnections() {

        return connections;
    }
}