package CourseRegisterationSystem;

import Model.*;
import Observer.WaitlistManager;
import Repository.CourseRepository;
import Repository.StudentRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseRegistrationDemo {
    // Defines the main demo class for the Course Registration System.


    public static void main(String[] args) {
        // Main method where the execution of the program starts.


        // 1. Setup the system
        // Creates the Facade object that provides a simple interface
        // for registering and dropping students from courses.


        CourseRegistrationSystemFacade system = new CourseRegistrationSystemFacade();
        // Creates the CourseRegistrationSystemFacade object.


        StudentRepository studentRepo = StudentRepository.getInstance();
        // Gets the Singleton instance of StudentRepository.
        // This repository is used to store and find students.


        CourseRepository courseRepo = CourseRepository.getInstance();
        // Gets the Singleton instance of CourseRepository.
        // This repository is used to store and find courses and course offerings.


        WaitlistManager waitlistManager = WaitlistManager.getInstance();
        // Gets the Singleton instance of WaitlistManager.
        // It handles students who are waiting for an available seat.


        // 2. Setup courses and professors
        // Creates the courses and professor that will be used in the demo.


        Course cs101 = new Course("CS101", "Intro to Programming");
        // Creates the CS101 course with its course code and title.


        Course cs201 = new Course("CS201", "Data Structures");
        // Creates the CS201 course with its course code and title.


        cs201.addPrerequisite(cs101);
        // Makes CS101 a prerequisite for CS201.
        // A student must complete CS101 before registering for CS201.


        courseRepo.saveCourse(cs101); courseRepo.saveCourse(cs201);
        // Saves both courses in the CourseRepository.


        Professor profA = new Professor("P1", "Dr. Smith");
        // Creates a professor with ID P1 and name Dr. Smith.


        // 3. Setup course offerings using the Builder
        // Creates specific offerings of the courses using the Builder Pattern.


        CourseOffering cs101Offering = new CourseOffering.Builder("CS101-F23", cs101)
                // Creates a Builder for the CS101 course offering.
                // "CS101-F23" is the ID of this particular offering.


                .withProfessor(profA).at(new TimeSlot(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 30)))
                // Assigns Dr. Smith as the professor.
                // Sets the class schedule to Monday from 10:00 AM to 11:30 AM.


                .withCapacity(1).build();
        // Sets the maximum capacity to 1 student.
        // build() creates the final CourseOffering object.


        CourseOffering cs201Offering = new CourseOffering.Builder("CS201-F23", cs201)
                // Creates a Builder for the CS201 course offering.


                .withProfessor(profA).at(new TimeSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(15, 30)))
                // Assigns Dr. Smith as the professor.
                // Sets the class schedule to Wednesday from 2:00 PM to 3:30 PM.


                .withCapacity(2).build();
        // Sets the maximum capacity to 2 students.
        // build() creates the final CourseOffering object.


        // Register the WaitlistManager as an observer for the course offering
        // Adds WaitlistManager as an observer of CS101.
        // It will be notified when a seat becomes available.


        cs101Offering.addObserver(waitlistManager);
        // Registers the WaitlistManager as an observer for CS101.


        courseRepo.saveOffering(cs101Offering); courseRepo.saveOffering(cs201Offering);
        // Saves both course offerings in the CourseRepository.


        // 4. Setup students
        // Creates the students who will participate in the registration scenarios.


        Student alice = new Student("S1", "Alice");
        // Creates a student named Alice with ID S1.


        Student bob = new Student("S2", "Bob");
        // Creates a student named Bob with ID S2.


        Student charlie = new Student("S3", "Charlie");
        // Creates a student named Charlie with ID S3.


        alice.addCompletedCourse(cs101); // Alice has the prerequisite for CS201
        // Adds CS101 to Alice's completed courses.
        // Therefore, Alice satisfies the prerequisite required for CS201.


        studentRepo.save(alice); studentRepo.save(bob); studentRepo.save(charlie);
        // Saves all three students in the StudentRepository.


        // 5. Run Registration Scenarios
        // Starts testing different registration situations.


        System.out.println("----------- SCENARIO 1: Successful Registration -----------");
        // Prints the heading for Scenario 1.


        system.registerStudentForCourse(alice.getId(), cs201Offering.getId());
        // Registers Alice for CS201.
        // Alice has already completed CS101, so the prerequisite check passes.
        // CS201 has available capacity, so Alice is successfully registered.


        System.out.println("\n----------- SCENARIO 2: Prerequisite Failure -----------");
        // Prints the heading for Scenario 2.


        system.registerStudentForCourse(bob.getId(), cs201Offering.getId());
        // Attempts to register Bob for CS201.
        // Bob has not completed CS101.
        // Therefore, the prerequisite rule rejects the registration.


        System.out.println("\n----------- SCENARIO 3: Course Capacity and Waitlist -----------");
        // Prints the heading for Scenario 3.


        system.registerStudentForCourse(bob.getId(), cs101Offering.getId()); // Bob gets the last spot
        // Registers Bob for CS101.
        // CS101 has a capacity of only 1, so Bob takes the only available seat.


        system.registerStudentForCourse(charlie.getId(), cs101Offering.getId()); // Charlie gets waitlisted
        // Attempts to register Charlie for CS101.
        // Since Bob already occupies the only seat, Charlie is added to the waitlist.


        System.out.println("\n----------- SCENARIO 4: Dropping a course and Observer pattern triggering waitlist promotion -----------");
        // Prints the heading for Scenario 4.


        system.dropStudentFromCourse(bob.getId(), cs101Offering.getId());
        // Removes Bob from CS101.
        // A seat becomes available.
        // The Observer Pattern notifies WaitlistManager.
        // WaitlistManager takes Charlie from the waitlist
        // and attempts to register Charlie for CS101.
    }
}