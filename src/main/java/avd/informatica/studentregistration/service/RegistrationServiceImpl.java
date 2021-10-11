package avd.informatica.studentregistration.service;

import avd.informatica.studentregistration.domain.Course;
import avd.informatica.studentregistration.domain.Student;
import avd.informatica.studentregistration.repository.CourseRepository;
import avd.informatica.studentregistration.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private CourseRepository courseRepository;

    @Override
    public boolean addRegistration(Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (student.isPresent() && course.isPresent()) {
            student.get().addCourse(course.get());
            studentRepository.save(student.get());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Student> getAllStudentsForCourse(Long courseId) {
        return null;
    }

    @Override
    public List<Student> getAllEnrolledCourses(Long studentId) {
        return null;
    }

    @Override
    public List<Student> getAllStudentsForCourse(String courseName) {
        return null;
    }
}
