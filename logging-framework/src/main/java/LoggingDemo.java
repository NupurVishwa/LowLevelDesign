public class LoggingDemo {

    public static void main(String[] args) {

        // Logger will process INFO, WARN and ERROR.
        // DEBUG messages will be ignored.
        Logger logger =
                new Logger(LogLevel.INFO);

        // Add Console target.
        logger.addTarget(
                LogTargetFactory.createTarget("CONSOLE")
        );

        // Add File target.
        logger.addTarget(
                LogTargetFactory.createTarget("FILE")
        );

        // Add Database target.
        logger.addTarget(
                LogTargetFactory.createTarget("DATABASE")
        );

        // DEBUG will NOT be printed
        // because minimum level is INFO.
        logger.debug("Debugging application");

        // These will be sent to all 3 targets.
        logger.info("User logged in");

        logger.warn("Database response is slow");

        logger.error("Payment failed");
    }
}
