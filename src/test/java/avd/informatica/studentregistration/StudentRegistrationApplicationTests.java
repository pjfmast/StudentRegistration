package avd.informatica.studentregistration;

import avd.informatica.studentregistration.domain.Course;
import avd.informatica.studentregistration.domain.Student;
import avd.informatica.studentregistration.repository.CourseRepository;
import avd.informatica.studentregistration.repository.StudentRepository;
import avd.informatica.studentregistration.service.RegistrationService;
import avd.informatica.studentregistration.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// todo create test for Registration
@SpringBootTest
class StudentRegistrationApplicationTests {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    private RegistrationService sut;

    @BeforeEach
    void beforeEach() {
        studentRepository = mock(StudentRepository.class);
        courseRepository = mock(CourseRepository.class);
        sut = new RegistrationServiceImpl(studentRepository, courseRepository);

        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArgument(0));
        when(courseRepository.save(any(Course.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void new_registration_updates_student() {
        // arrange
        Student student = mock(Student.class);
        student.setId(1L);

        Course course = mock(Course.class);
        course.setId(100L);

        sut.addRegistration(1L, 100L);

        //
        verify(studentRepository, times(1)).save(student);
    }

}
