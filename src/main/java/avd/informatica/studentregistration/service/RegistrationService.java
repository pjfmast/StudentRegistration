package avd.informatica.studentregistration.service;

import avd.informatica.studentregistration.domain.Student;

import java.util.List;
import java.util.Set;

public interface RegistrationService {
    boolean addRegistration(Long studentId, Long courseId);

    boolean removeRegistration(Long studentId, Long courseId);

    Set<Student> getAllStudentsForCourse(Long courseId);

    Set<Student> getAllEnrolledCourses(Long studentId);

    Set<Student> getAllStudentsForCourse(String courseName);
}
