package Exception;

public class RegistrationException extends Exception {
    // Defines a custom exception class for registration-related errors.
    // It extends Exception, so it is a checked exception.


    public RegistrationException(String message) {
        // Constructor that accepts an error message.


        super(message);
        // Passes the error message to the parent Exception class.
        // This message can later be displayed when the exception occurs.
    }
}