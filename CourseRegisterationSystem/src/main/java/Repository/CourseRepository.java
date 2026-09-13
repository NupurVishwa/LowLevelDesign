package Repository;
// Defines the package where the CourseRepository class belongs.


import Model.Course;
// Imports the Course class so we can store Course objects.


import Model.CourseOffering;
// Imports the CourseOffering class so we can store course offering objects.


import java.util.Map;
// Imports the Map interface for storing data as key-value pairs.


import java.util.Optional;
// Imports Optional to safely return a value that may or may not exist.


import java.util.concurrent.ConcurrentHashMap;
// Imports ConcurrentHashMap, which is a thread-safe implementation of Map.


public class CourseRepository {
    // Defines the CourseRepository class.
    // This class is responsible for storing and retrieving courses and course offerings.


    private static final CourseRepository INSTANCE = new CourseRepository();
    // Creates a single instance of CourseRepository.
    // 'static' means it belongs to the class rather than an object.
    // 'final' means the INSTANCE reference cannot be changed.
    // This is part of the Singleton design pattern.


    private final Map<String, Course> courses = new ConcurrentHashMap<>();
    // Creates a thread-safe map for storing courses.
    // The String is the course code.
    // The Course is the actual Course object.


    private final Map<String, CourseOffering> offerings = new ConcurrentHashMap<>();
    // Creates a thread-safe map for storing course offerings.
    // The String is the offering ID.
    // The CourseOffering is the actual course offering object.


    public static CourseRepository getInstance() {
        return INSTANCE;
    }
    // Provides access to the single CourseRepository instance.
    // Instead of creating a new repository, other classes use this method
    // to get the already existing instance.


    public void saveCourse(Course c) {
        courses.put(c.getCourseCode(), c);
    }
    // Saves a Course object in the courses map.
    // The course code is used as the key.
    // The Course object is stored as the value.


    public void saveOffering(CourseOffering o) {
        offerings.put(o.getId(), o);
    }
    // Saves a CourseOffering object in the offerings map.
    // The offering ID is used as the key.
    // The CourseOffering object is stored as the value.


    public Optional<CourseOffering> findOfferingById(String id) {
        return Optional.ofNullable(offerings.get(id));
    }
    // Searches for a course offering using its ID.
    // offerings.get(id) returns the CourseOffering if it exists.
    // If no offering exists, it returns null.
    // Optional.ofNullable safely wraps either the object or null.
}