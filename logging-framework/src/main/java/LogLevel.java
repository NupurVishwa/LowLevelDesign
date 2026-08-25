// Defines the severity/priority of a log message.
public enum LogLevel {

    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
//DEBUG → detailed information
//INFO  → normal application information
//WARN  → something unexpected
//ERROR → serious problem