public class TaskManagementSystemDemo {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();


        // ==========================================
        // Create Users
        // ==========================================

        User alice = new User("Alice");

        User bob = new User("Bob");


        // ==========================================
        // Create Task
        // ==========================================

        Task task = manager.createTask("Implement Login", "Add login functionality", TaskPriority.HIGH, alice);

        System.out.println("Task created: " + task.getTitle());


        // ==========================================
        // Reassign task
        // ==========================================

        manager.assignTask(task.getId(), bob);


        // ==========================================
        // Update status
        // ==========================================

        manager.updateTaskStatus(task.getId(), TaskStatus.IN_PROGRESS);


        // ==========================================
        // Add comment
        // ==========================================

        Comment comment = new Comment("Started working on login", bob);

        manager.addCommentToTask(task.getId(), comment);



        // ==========================================
        // Update priority
        // ==========================================

        manager.updateTaskPriority(task.getId(), TaskPriority.MEDIUM);


        // ==========================================
        // Print task information
        // ==========================================

        System.out.println("Task: " + task.getTitle());

        System.out.println("Status: " + task.getStatus());

        System.out.println("Priority: " + task.getPriority());

        System.out.println("Assignee: " + task.getAssignee().getName());


        // ==========================================
        // Filter by status
        // ==========================================

        System.out.println("\nIN_PROGRESS tasks:");

        for (Task t : manager.listTasksByStatus(TaskStatus.IN_PROGRESS)) {
            System.out.println(t.getTitle());
        }


        // ==========================================
        // Filter by assignee
        // ==========================================

        System.out.println("\nTasks assigned to Bob:");

        for (Task t : manager.listTasksByAssignee(bob)) {

            System.out.println(t.getTitle());
        }
    }
}
