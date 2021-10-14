package avd.informatica.studentregistration.service;

import avd.informatica.studentregistration.domain.Student;

import java.util.List;
import java.util.Set;
// todo workshop lesson 7-3a: create RegistrationService

public interface RegistrationService {
    boolean addRegistration(Long studentId, Long courseId);

    boolean removeRegistration(Long studentId, Long courseId);

}
