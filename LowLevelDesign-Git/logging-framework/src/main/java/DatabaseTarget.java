public class DatabaseTarget implements LogTarget {

    @Override
    public void write(LogMessage logMessage) {

        // In a real application, we would execute:
        //
        // INSERT INTO logs(level, message, timestamp)
        // VALUES(...);
        //
        // For this LLD demo, we simply print it.

        System.out.println(
                "Saving to database: "
                        + logMessage.getMessage()
        );
    }
}