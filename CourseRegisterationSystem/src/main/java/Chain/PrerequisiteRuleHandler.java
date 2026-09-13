package Chain;

import Exception.RegistrationException;
import Model.Course;

import java.util.Set;

public class PrerequisiteRuleHandler extends RegistrationRuleHandler {
    // Creates a handler responsible for checking course prerequisites.


    @Override
    // Indicates that this method overrides the handle() method
    // from the RegistrationRuleHandler parent class.

    public void handle(RegistrationRequest request) throws RegistrationException {
        // Handles the registration request.
        // RegistrationException is thrown if the prerequisite check fails.


        Set<Course> completed = request.student().getCompletedCourses();
        // Gets all the courses that the student has already completed.
        // Stores them in the 'completed' set.


        Set<Course> prereqs = request.offering().getCourse().getPrerequisites();
        // Gets all prerequisite courses required for the course
        // that the student is trying to register for.


        if (!completed.containsAll(prereqs)) {
            // Checks whether the student has completed ALL
            // the required prerequisite courses.


            throw new RegistrationException("Prerequisite not met for course " + request.offering().getCourse().getCourseCode());
            // If even one prerequisite is missing, registration is rejected.
            // An exception is thrown with the course code in the message.
        }


        handleNext(request);
        // If all prerequisites are satisfied,
        // passes the request to the next handler in the chain.
    }
}
