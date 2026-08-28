public interface LogTarget {

    // Every target must know how to write a log.
    void write(LogMessage logMessage);
}
//Why Strategy?
//
//Because the Logger shouldn't care whether the log goes to:
//
//Console
//File
//Database
//URL
//Kafka
//
//We can add a new target without modifying the Logger