package Chain;

import Model.CourseOffering;
import Model.Student;

public record RegistrationRequest(Student student, CourseOffering offering) {
    // Declares a record named RegistrationRequest with student and course offering as its components.
}