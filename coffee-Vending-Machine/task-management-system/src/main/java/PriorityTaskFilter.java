public class PriorityTaskStrategy implements TaskFilter{
    private final TaskPriority taskPriority;

    public PriorityTaskStrategy(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    @Override
    public boolean matches(Task task) {
        return task.getPriority()==taskPriority;
    }
}
