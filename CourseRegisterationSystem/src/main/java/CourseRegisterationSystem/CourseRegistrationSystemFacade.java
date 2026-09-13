package CourseRegisterationSystem;
import Exception.RegistrationException;
import Chain.RegistrationRequest;
import Repository.CourseRepository;
import Repository.StudentRepository;

public class CourseRegistrationSystemFacade {
    // Defines the Facade class.
    // This class provides a simple interface for registering
    // and dropping students from courses.


    private final CourseRegistrationService courseRegistrationService = new CourseRegistrationService();
    // Creates a CourseRegistrationService object.
    // This service contains the actual registration logic.


    private final StudentRepository studentRepo = StudentRepository.getInstance();
    // Gets the single StudentRepository instance using the Singleton Pattern.
    // It is used to find students using their IDs.


    private final CourseRepository courseRepo = CourseRepository.getInstance();
    // Gets the single CourseRepository instance.
    // It is used to find course offerings using their IDs.


    public void registerStudentForCourse(String studentId, String offeringId) {
        // Provides a simple method to register a student for a course.
        // The caller only needs to provide the student ID and offering ID.


        studentRepo.findById(studentId).ifPresentOrElse(student ->
                // Searches for the student using the provided student ID.
                // If the student is found, the 'student' variable contains that Student object.
                // If the student is not found, the second part of ifPresentOrElse is executed.


                courseRepo.findOfferingById(offeringId).ifPresentOrElse(offering -> {
                            // Searches for the course offering using the provided offering ID.
                            // If the offering is found, the 'offering' variable contains that CourseOffering object.


                            try {
                                // Starts a try block because the registration process
                                // can throw a RegistrationException.


                                courseRegistrationService.register(new RegistrationRequest(student, offering));
                                // Creates a RegistrationRequest using the student and course offering.
                                // Sends the request to the CourseRegistrationService.
                                // The service then runs the registration rules.


                            } catch (RegistrationException e) {
                                // Catches the exception if registration fails.


                                System.err.println("Registration Failed for " + student.getName() + ": " + e.getMessage());
                                // Prints an error message showing the student's name
                                // and the reason why registration failed.


                            }
                        }, () -> System.err.println("Error: Course offering " + offeringId + " not found.")
                        // If the course offering is not found,
                        // prints an error message with the offering ID.


                ), () -> System.err.println("Error: Student " + studentId + " not found."));
        // If the student is not found,
        // prints an error message with the student ID.
    }


    public void dropStudentFromCourse(String studentId, String offeringId) {
        // Provides a simple method to drop a student from a course.
        // The caller only needs to provide the student ID and offering ID.


        studentRepo.findById(studentId).ifPresent(student ->
                // Searches for the student using the student ID.
                // If the student exists, the code inside the lambda is executed.


                courseRepo.findOfferingById(offeringId).ifPresent(offering -> {
                    // Searches for the course offering using the offering ID.
                    // If the offering exists, the code inside the lambda is executed.


                    offering.dropStudent(student);
                    // Removes the student from the course offering.
                    // The CourseOffering class also handles notifying observers
                    // if a seat becomes available.


                    System.out.println(student.getName() + " dropped from " + offering.getCourse().getCourseCode());
                    // Prints a confirmation message showing the student's name
                    // and the course code from which they were dropped.
                })
        );
    }
}