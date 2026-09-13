package Repository;

import Model.Student;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepository {
    // Defines the StudentRepository class.
    // This class is responsible for storing and retrieving Student objects.


    private static final StudentRepository INSTANCE = new StudentRepository();
    // Creates a single instance of StudentRepository.
    // This is part of the Singleton Pattern.
    // The same repository instance is shared throughout the application.


    private final Map<String, Student> students = new ConcurrentHashMap<>();
    // Creates a map to store students.
    // The student's ID is used as the key.
    // The Student object is stored as the value.
    // ConcurrentHashMap allows the map to be safely accessed by multiple threads.


    public static StudentRepository getInstance() {
        return INSTANCE;
    }
    // Returns the single StudentRepository instance.
    // Other classes can use this method to access the repository.


    public void save(Student s) {
        students.put(s.getId(), s);
    }
    // Saves a student in the map.
    // The student's ID is used as the key.
    // If a student with the same ID already exists, it will be replaced.


    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }
    // Searches for a student using the student's ID.
    // students.get(id) returns the Student if found, otherwise null.
    // Optional.ofNullable safely wraps the result.
    // This avoids directly returning null when the student does not exist.
}