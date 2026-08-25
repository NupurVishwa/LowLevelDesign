import java.util.ArrayList;
import java.util.List;

public class Logger {

    private final List<LogTarget> targets = new ArrayList<>();

    // Minimum level that this logger will process.
    private final LogLevel minimumLevel;

    public Logger(LogLevel minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    // Add a target such as Console, File or Database.
    public void addTarget(LogTarget target) {
        targets.add(target);
    }

    public void log(
            LogLevel level,
            String message) {

        // Ignore logs below configured level.
        //
        // Example:
        // minimumLevel = INFO
        //
        // DEBUG will be ignored.
        if (level.getPriority()
                < minimumLevel.getPriority()) {

            return;
        }

        // Create a LogMessage object.
        LogMessage logMessage =
                new LogMessage(level, message);

        // Send the same log to every configured target.
        for (LogTarget target : targets) {

            target.write(logMessage);
        }
    }

    // Convenience methods.
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}