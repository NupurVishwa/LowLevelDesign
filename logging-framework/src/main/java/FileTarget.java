import java.io.FileWriter;
import java.io.IOException;

public class FileTarget implements LogTarget {

    private final String fileName;

    public FileTarget(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(LogMessage logMessage) {

        try (FileWriter writer = new FileWriter(fileName, true)) {

            writer.write(
                    logMessage.getTimestamp()
                            + " ["
                            + logMessage.getLevel()
                            + "] "
                            + logMessage.getMessage()
                            + "\n"
            );

        } catch (IOException e) {

            System.out.println(
                    "Unable to write log to file"
            );
        }
    }
}