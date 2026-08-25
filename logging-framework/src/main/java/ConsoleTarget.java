public class ConsoleTarget implements LogTarget {

    @Override
    public void write(LogMessage logMessage) {

        System.out.println(
                logMessage.getTimestamp()
                        + " ["
                        + logMessage.getLevel()
                        + "] "
                        + logMessage.getMessage()
        );
    }
}
//2026-08-20T22:30:10 [INFO] User logged in