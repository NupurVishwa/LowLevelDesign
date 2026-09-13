package Chain;
import Exception.RegistrationException;
public class CapacityRuleHandler extends RegistrationRuleHandler {
    // Creates a handler responsible for checking
    // whether the course has available seats.


    @Override
    // Indicates that this method overrides the handle() method
    // from the RegistrationRuleHandler parent class.

    public void handle(RegistrationRequest request) throws RegistrationException {
        // Handles the student's registration request.


        if (request.offering().isFull()) {
            // Checks whether the course has reached its maximum capacity.


            request.offering().addToWaitlist(request.student());
            // If the course is full, adds the student to the waitlist.


            System.out.println("Course " + request.offering().getCourse().getCourseCode() + " is full. " + request.student().getName() + " added to waitlist.");
            // Prints a message saying that the course is full
            // and the student has been added to the waitlist.


        } else {
            // Executes when the course still has an available seat.


            request.offering().addStudent(request.student());
            // Adds the student to the course.


            System.out.println(request.student().getName() + " successfully registered for " + request.offering().getCourse().getCourseCode());
            // Prints a message confirming that the student
            // has successfully registered for the course.
        }


        handleNext(request);
        // Passes the registration request to the next handler
        // in the Chain of Responsibility.
    }
}