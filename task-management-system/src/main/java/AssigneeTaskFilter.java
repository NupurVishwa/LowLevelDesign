public class AssigneeTaskFilter implements TaskFilter {
    private final User user;

    public AssigneeTaskFilter(User user) {
        this.user = user;
    }

    @Override
    public boolean matches(Task task) {
        if (task.getAssignee() == null) {
            return false;
        }

        return task.getAssignee().getId().equals(user.getId());
    }
}
