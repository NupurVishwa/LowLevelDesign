package Model;

import java.util.HashSet;
import java.util.Set;

public class Student {
    // Represents a student in the course registration system.


    private final String id;
    // Stores the unique ID of the student.


    private final String name;
    // Stores the name of the student.


    private final Set<Course> completedCourses = new HashSet<>();
    // Stores all courses that the student has already completed.
    // HashSet prevents the same course from being added multiple times.


    private final Set<CourseOffering> registeredOfferings = new HashSet<>();
    // Stores all course offerings in which the student is currently registered.
    // HashSet prevents duplicate course offerings.


    public Student(String id, String name) { this.id = id; this.name = name; }
    // Constructor used to create a Student object.
    // Stores the student's ID and name.


    public String getId() { return id; }
    // Returns the student's ID.


    public String getName() { return name; }
    // Returns the student's name.


    public Set<Course> getCompletedCourses() { return completedCourses; }
    // Returns the set of courses that the student has completed.


    public Set<CourseOffering> getRegisteredOfferings() { return registeredOfferings; }
    // Returns the set of course offerings in which the student is registered.


    public void addCompletedCourse(Course course) { completedCourses.add(course); }
    // Adds a completed course to the student's completed courses.


    public void enroll(CourseOffering offering) { registeredOfferings.add(offering); }
    // Adds a course offering to the student's registered courses.
    // This is called when the student successfully registers.


    public void drop(CourseOffering offering) { registeredOfferings.remove(offering); }
    // Removes a course offering from the student's registered courses.
    // This is called when the student drops the course.
}