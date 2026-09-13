package CourseRegisterationSystem;

import Chain.*;
import Exception.RegistrationException;

public class CourseRegistrationService {
    // Defines the service class responsible for handling course registration.


    private final RegistrationRuleHandler registrationChain;
    // Stores the first handler of the Chain of Responsibility.
    // The registration request will pass through all the handlers in the chain.


    public CourseRegistrationService() {
        // Constructor used to create the CourseRegistrationService object.


        // Build the chain of responsibility
        // Creates and connects all registration rule handlers in the required order.


        RegistrationRuleHandler capacityHandler = new CapacityRuleHandler();
        // Creates the handler responsible for checking course capacity.


        RegistrationRuleHandler conflictHandler = new ScheduleConflictRuleHandler();
        // Creates the handler responsible for checking schedule conflicts.


        conflictHandler.setNext(capacityHandler);
        // Connects the schedule conflict handler to the capacity handler.
        // If there is no schedule conflict, the request moves to the capacity handler.


        RegistrationRuleHandler prereqHandler = new PrerequisiteRuleHandler();
        // Creates the handler responsible for checking course prerequisites.


        prereqHandler.setNext(conflictHandler);
        // Connects the prerequisite handler to the schedule conflict handler.
        // If prerequisites are satisfied, the request moves to the conflict handler.


        this.registrationChain = prereqHandler;
        // Makes the prerequisite handler the first handler in the chain.
        // Therefore, registration starts with the prerequisite check.
    }


    public void register(RegistrationRequest request) throws RegistrationException {
        // Method used to register a student for a course.
        // It can throw RegistrationException if any registration rule fails.


        registrationChain.handle(request);
        // Sends the registration request to the first handler.
        // The request then moves through the entire chain.
    }
}