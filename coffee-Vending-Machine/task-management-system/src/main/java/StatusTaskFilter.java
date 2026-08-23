public class StatusTaskStrategy implements TaskFilter {
    public final TaskStatus status;

    public StatusTaskStrategy(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean matches(Task task) {
        return task.getStatus() == status;
    }
}
