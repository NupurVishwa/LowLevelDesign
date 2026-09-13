package Model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    // Defines a Course class that represents a course in the registration system.


    private final String courseCode;
    // Stores the unique code of the course.
    // 'final' means the course code cannot be changed after initialization.


    private final String title;
    // Stores the title or name of the course.
    // 'final' means the title cannot be changed after initialization.


    private final Set<Course> prerequisites = new HashSet<>();
    // Stores all courses that must be completed before taking this course.
    // HashSet is used to avoid duplicate prerequisite courses.


    public Course(String courseCode, String title) { this.courseCode = courseCode; this.title = title; }
    // Constructor used to create a Course object.
    // courseCode and title are assigned when the course is created.


    public void addPrerequisite(Course course) { prerequisites.add(course); }
    // Adds a course to the prerequisite set.
    // This means the student must complete that course first.


    public String getCourseCode() { return courseCode; }
    // Returns the course code.


    public Set<Course> getPrerequisites() { return prerequisites; }
    // Returns the set containing all prerequisites of this course.
}