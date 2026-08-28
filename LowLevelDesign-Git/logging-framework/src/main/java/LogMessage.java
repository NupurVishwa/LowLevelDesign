import java.time.LocalDateTime;

public class LogMessage {

    private final LogLevel level;
    private final String message;
    private final LocalDateTime timestamp;

    public LogMessage(LogLevel level, String message) {

        this.level = level;
        this.message = message;

        // Capture the time when log was created.
        this.timestamp = LocalDateTime.now();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

//INFO | 10:30 | User logged in