import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {

    private final String id;

    private String title;
    private String description;

    private TaskStatus status;
    private TaskPriority priority;

    private User assignee;

    /*
     * One task can have multiple comments.
     */
    private final List<Comment> comments;


    public Task(String title, String description, TaskPriority priority, User assignee) {

        this.id = UUID.randomUUID().toString();

        this.title = title;

        this.description = description;

        this.priority = priority;

        this.assignee = assignee;

        /*
         * Every newly created task starts as TODO.
         */
        this.status = TaskStatus.TODO;

        this.comments = new ArrayList<>();
    }


    // ==========================================
    // Update Status
    // ==========================================

    public void updateStatus(TaskStatus status) {

        this.status = status;
    }


    // ==========================================
    // Update Priority
    // ==========================================

    public void updatePriority(TaskPriority priority) {

        this.priority = priority;
    }


    // ==========================================
    // Assign User
    // ==========================================

    public void assignUser(User user) {

        this.assignee = user;
    }


    // ==========================================
    // Add Comment
    // ==========================================

    public void addComment(Comment comment) {

        comments.add(comment);
    }


    // ==========================================
    // Getters
    // ==========================================

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
