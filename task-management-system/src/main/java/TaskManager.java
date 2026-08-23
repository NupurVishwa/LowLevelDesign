import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TaskManager {

    /*
     * Map gives us O(1) average lookup by task ID.
     *
     * taskId -> Task
     */
    private final Map<String, Task> tasks;

    public TaskManager() {

        tasks = new HashMap<>();
    }


    // ==========================================
    // CREATE TASK
    // ==========================================

    public Task createTask(String title, String description, TaskPriority priority, User assignee) {
        Task task = new Task(title, description, priority, assignee);

        /*
         * Store the task in our task repository.
         */
        tasks.put(task.getId(), task);

        return task;
    }


    // ==========================================
    // FIND TASK
    // ==========================================

    private Task getTask(String taskId) {

        Task task = tasks.get(taskId);

        if (task == null) {

            throw new IllegalArgumentException("Task not found: " + taskId);
        }

        return task;
    }


    // ==========================================
    // ASSIGN TASK
    // ==========================================

    public void assignTask(
            String taskId, User user) {

        Task task = getTask(taskId);

        task.assignUser(user);
    }


    // ==========================================
    // UPDATE STATUS
    // ==========================================

    public void updateTaskStatus(String taskId, TaskStatus status) {

        Task task = getTask(taskId);

        task.updateStatus(status);
    }


    // ==========================================
    // UPDATE PRIORITY
    // ==========================================

    public void updateTaskPriority(String taskId, TaskPriority priority) {

        Task task = getTask(taskId);

        task.updatePriority(priority);
    }


    // ==========================================
    // ADD COMMENT
    // ==========================================

    public void addCommentToTask(String taskId, Comment comment) {

        Task task = getTask(taskId);

        task.addComment(comment);
    }


    // ==========================================
    // LIST ALL TASKS
    // ==========================================

    public List<Task> listTasks() {

        return new ArrayList<>(tasks.values());
    }


    // ==========================================
    // FILTER TASKS
    // ==========================================

    public List<Task> filterTasks(TaskFilter filter) {

        /*
         * TaskManager does NOT need to know
         * how filtering works.
         *
         * It simply asks the strategy:
         *
         * "Does this task match?"
         */

        return tasks.values().stream().filter(filter::matches).collect(Collectors.toList());
    }


    // ==========================================
    // Convenience methods
    // ==========================================

    public List<Task> listTasksByStatus(TaskStatus status) {

        return filterTasks(new StatusTaskFilter(status));
    }


    public List<Task> listTasksByPriority(TaskPriority priority) {
        return filterTasks(new PriorityTaskFilter(priority));
    }


    public List<Task> listTasksByAssignee(User user) {
        return filterTasks(new AssigneeTaskFilter(user));
    }
}
