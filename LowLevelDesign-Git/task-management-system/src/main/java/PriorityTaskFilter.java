public class PriorityTaskFilter implements TaskFilter{
    private final TaskPriority taskPriority;

    public PriorityTaskFilter(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    @Override
    public boolean matches(Task task) {
        return task.getPriority()==taskPriority;
    }
}
