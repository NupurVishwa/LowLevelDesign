package Observer;

import Chain.RegistrationRequest;
import CourseRegisterationSystem.CourseRegistrationService;
import Model.CourseOffering;
import Exception.RegistrationException;

public class WaitlistManager implements CourseOfferingObserver {
    // Defines the WaitlistManager class.
    // It implements CourseOfferingObserver so it can be notified
    // when a seat becomes available in a course offering.


    private static final WaitlistManager INSTANCE = new WaitlistManager();
    // Creates a single instance of WaitlistManager.
    // This is part of the Singleton Pattern.
    // The same WaitlistManager object will be used throughout the application.


    private final CourseRegistrationService courseRegistrationService = new CourseRegistrationService(); // To re-register a waitlisted student
    // Creates a CourseRegistrationService object.
    // It is used to register a student who is being promoted from the waitlist.


    public static WaitlistManager getInstance() { return INSTANCE; }
    // Returns the single WaitlistManager instance.
    // Other classes use this method to get the Singleton object.


    private WaitlistManager() {}
    // Private constructor prevents other classes from creating
    // another WaitlistManager object using 'new'.


    @Override
    // Indicates that this method overrides the method
    // defined in the CourseOfferingObserver interface.


    public void onSpotAvailable(CourseOffering offering) {
        // This method is automatically called when a seat becomes available.
        // 'offering' represents the course where the seat became available.


        System.out.println("OBSERVER (WaitlistManager): Spot available in " + offering.getCourse().getCourseCode() + ". Processing waitlist.");
        // Prints a message saying that a seat is available
        // and the waitlist will now be processed.


        offering.getNextFromWaitlist().ifPresent(student -> {
            // Gets the next student from the waitlist.
            // If a student exists, the code inside this block is executed.


            System.out.println("Promoting " + student.getName() + " from waitlist for " + offering.getCourse().getCourseCode());
            // Prints a message showing which student is being promoted
            // from the waitlist.


            try {
                // Starts a try block because registration can throw
                // a RegistrationException.


                // We can re-use the registration service, but a real system might have a simplified "promote" logic
                courseRegistrationService.register(new RegistrationRequest(student, offering));
                // Creates a RegistrationRequest using the student and course offering.
                // Sends the request to the registration service.
                // This allows the normal registration rules to be checked again.


            } catch (RegistrationException e) {
                // Catches the RegistrationException if registration fails.


                System.err.println("Failed to promote " + student.getName() + ": " + e.getMessage());
                // Prints an error message explaining why the student
                // could not be promoted from the waitlist.


                // Handle failure: try next student or notify admin
                // This is a comment explaining that a real system
                // could try the next student or notify an administrator.
            }
        });
        // Ends the ifPresent block and the lambda expression.
    }
}