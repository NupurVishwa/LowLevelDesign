public class LogTargetFactory {

    public static LogTarget createTarget(String targetType) {

        // Factory decides which concrete target to create.
        switch (targetType.toUpperCase()) {

            case "CONSOLE":
                return new ConsoleTarget();

            case "FILE":
                return new FileTarget("application.log");

            case "DATABASE":
                return new DatabaseTarget();

            default:
                throw new IllegalArgumentException(
                        "Unknown target: " + targetType
                );
        }
    }
}
//Why Factory?
//
//Instead of doing this everywhere:
//
//new ConsoleTarget();
//new FileTarget();
//new DatabaseTarget();
//
//we do:
//
//LogTargetFactory.createTarget("FILE");
//
//Later we can add:
//
//HTTP
//KAFKA
//ELASTICSEARCH
