package Model;


import Observer.CourseOfferingObserver;

import java.util.*;

public class CourseOffering {
    // Represents a specific offering of a course.
    // For example, the same course can be offered by different professors
    // at different times.


    private final String id;
    // Stores the unique ID of this course offering.


    private final Course course;
    // Stores the Course associated with this offering.


    private final Professor professor;
    // Stores the professor who is teaching this course offering.


    private final TimeSlot timeSlot;
    // Stores the time at which this course is scheduled.


    private final int capacity;
    // Stores the maximum number of students allowed in this course.


    private final List<Student> registeredStudents = new ArrayList<>();
    // Stores all students who are successfully registered for this course.
    // ArrayList is used because we need to maintain a list of students.


    private final Queue<Student> waitlistedStudents = new LinkedList<>();
    // Stores students waiting for a seat when the course is full.
    // Queue follows FIFO: First In, First Out.


    private final List<CourseOfferingObserver> observers = new ArrayList<>();
    // Stores all observers who want to know when a seat becomes available.


    private CourseOffering(Builder builder) {
        // Private constructor used by the Builder Pattern.
        // The CourseOffering object is created through the Builder.


        this.id = builder.id;
        // Gets the course offering ID from the Builder.


        this.course = builder.course;
        // Gets the Course object from the Builder.


        this.professor = builder.professor;
        // Gets the Professor object from the Builder.


        this.timeSlot = builder.timeSlot;
        // Gets the TimeSlot from the Builder.


        this.capacity = builder.capacity;
        // Gets the maximum capacity from the Builder.
    }


    public void addObserver(CourseOfferingObserver observer) { observers.add(observer); }
    // Adds an observer to the list.
    // The observer will be notified when a seat becomes available.


    private void notifyObservers() { observers.forEach(o -> o.onSpotAvailable(this)); }
    // Notifies every observer that a seat has become available.
    // forEach goes through each observer in the list.
    // onSpotAvailable(this) tells the observer about this course offering.


    public boolean isFull() { return registeredStudents.size() >= capacity; }
    // Checks whether the number of registered students
    // has reached or exceeded the course capacity.
    // Returns true if the course is full, otherwise false.


    public void addStudent(Student student) { registeredStudents.add(student); student.enroll(this); }
    // Adds the student to the registered students list.
    // Then tells the Student object to enroll in this course offering.


    public void addToWaitlist(Student student) { waitlistedStudents.add(student); }
    // Adds the student to the waiting queue when the course is full.


    public void dropStudent(Student student) {
        // Removes a student from the course.


        boolean wasFull = isFull();
        // Checks whether the course was full before removing the student.
        // This is important because removing a student from a full course
        // creates a newly available seat.


        registeredStudents.remove(student);
        // Removes the student from the registered students list.


        student.drop(this);
        // Tells the Student object that the student has dropped this course.


        if (wasFull && !isFull()) {
            // Checks whether the course was previously full
            // and is no longer full after removing the student.


            notifyObservers();
            // Notifies all observers that a seat is now available.
        }
    }


    public Optional<Student> getNextFromWaitlist() { return Optional.ofNullable(waitlistedStudents.poll()); }
    // Removes and returns the first student from the waitlist.
    // poll() removes the student from the front of the queue.
    // Optional is used because the waitlist may be empty.


    public String getId() { return id; }
    // Returns the ID of the course offering.


    public Course getCourse() { return course; }
    // Returns the Course associated with this offering.


    public TimeSlot getTimeSlot() { return timeSlot; }
    // Returns the scheduled time slot of this course offering.


    // Builder Pattern
    // The Builder Pattern is used to create a CourseOffering object
    // step-by-step without needing a large constructor with many parameters.


    public static class Builder {
        // Defines the Builder class inside CourseOffering.
        // static means the Builder can be used without creating
        // a CourseOffering object first.


        private String id;
        private Course course;
        private Professor professor;
        private TimeSlot timeSlot;
        private int capacity;
        // Stores the values that will later be used to create
        // the CourseOffering object.


        public Builder(String id, Course course) { this.id = id; this.course = course; }
        // Builder constructor.
        // ID and Course are required when creating the Builder.


        public Builder withProfessor(Professor professor) { this.professor = professor; return this; }
        // Sets the professor for the course offering.
        // Returns the same Builder object so method calls can be chained.


        public Builder at(TimeSlot timeSlot) { this.timeSlot = timeSlot; return this; }
        // Sets the time slot for the course offering.
        // Returns the same Builder object for method chaining.


        public Builder withCapacity(int capacity) { this.capacity = capacity; return this; }
        // Sets the maximum number of students.
        // Returns the same Builder object for method chaining.


        public CourseOffering build() { return new CourseOffering(this); }
        // Creates and returns the final CourseOffering object.
        // 'this' passes the current Builder object to the private constructor.
    }
}