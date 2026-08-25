public class StatusTaskFilter implements TaskFilter {
    public final TaskStatus status;

    public StatusTaskFilter(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean matches(Task task) {
        return task.getStatus() == status;
    }
}
