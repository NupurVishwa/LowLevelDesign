package Chain;

// Imports RegistrationException from the exception package.
import Exception.RegistrationException;

// Abstract base class for all registration rule handlers.
public abstract class RegistrationRuleHandler {

    // Stores the next handler in the chain.
    private RegistrationRuleHandler next;

    // Sets the next handler in the chain.
    public void setNext(RegistrationRuleHandler next) { this.next = next; }

    // Abstract method that each concrete handler must implement
    // to process the registration request.
    public abstract void handle(RegistrationRequest request) throws RegistrationException;

    // Passes the registration request to the next handler if one exists.
    protected void handleNext(RegistrationRequest request) throws RegistrationException {
        // Checks whether a next handler is present.
        if (next != null) {
            // Sends the request to the next handler.
            next.handle(request);
        }
    }
}