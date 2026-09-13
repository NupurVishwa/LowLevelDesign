package Chain;
import Exception.RegistrationException;
public class ScheduleConflictRuleHandler extends RegistrationRuleHandler { // Declares a handler class for checking schedule conflicts.
    @Override // Indicates that this method overrides the handle method from the parent class.
    public void handle(RegistrationRequest request) throws RegistrationException {
        // Processes the registration request and can throw a RegistrationException.
        boolean conflict = request.student().getRegisteredOfferings().stream() // Gets the student's registered courses and creates a stream to process them.
                .anyMatch(offering -> offering.getTimeSlot().overlaps(request.offering().getTimeSlot())); // Checks whether any registered course has a time slot that overlaps with the new course.
        if (conflict) { // Checks whether a schedule conflict was found.
            throw new RegistrationException("Schedule conflict detected for course " + request.offering().getCourse().getCourseCode()); // Throws an exception with the course code when a schedule conflict exists.
        } // Ends the if condition.
        handleNext(request); // Passes the request to the next handler in the chain if no conflict was found.
    } // Ends the handle method.
} // Ends the ScheduleConflictRuleHandler class.