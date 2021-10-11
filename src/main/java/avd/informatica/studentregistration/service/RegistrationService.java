package avd.informatica.studentregistration.service;

import avd.informatica.studentregistration.domain.Student;

import java.util.List;
import java.util.Set;

public interface RegistrationService {
    boolean addRegistration(Long studentId, Long courseId);
    List<Student> getAllStudentsForCourse(Long courseId);
    List<Student> getAllEnrolledCourses(Long studentId);
    List<Student> getAllStudentsForCourse(String courseName);
}
