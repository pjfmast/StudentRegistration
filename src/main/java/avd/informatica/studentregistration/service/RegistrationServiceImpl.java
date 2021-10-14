package avd.informatica.studentregistration.service;

import avd.informatica.studentregistration.domain.Course;
import avd.informatica.studentregistration.domain.Student;
import avd.informatica.studentregistration.repository.CourseRepository;
import avd.informatica.studentregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// todo workshop lesson 7-3b: create RegistrationService

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegistrationServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean addRegistration(Long studentId, Long courseId) {
        Optional<Student> maybeStudent = studentRepository.findById(studentId);
        Optional<Course> maybeCourse = courseRepository.findById(courseId);

        if (maybeStudent.isPresent() && maybeCourse.isPresent()) {
            maybeStudent.get().addCourse(maybeCourse.get());
            studentRepository.save(maybeStudent.get());
            courseRepository.save(maybeCourse.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeRegistration(Long studentId, Long courseId) {
        Optional<Student> maybeStudent = studentRepository.findById(studentId);
        Optional<Course> maybeCourse = courseRepository.findById(courseId);

        if (maybeStudent.isPresent() && maybeCourse.isPresent()) {
            maybeStudent.get().removeCourse(maybeCourse.get());
            studentRepository.save(maybeStudent.get());
            courseRepository.save(maybeCourse.get());

            return true;
        } else {
            return false;
        }
    }

}
