package avd.informatica.studentregistration;


import avd.informatica.studentregistration.domain.*;
import avd.informatica.studentregistration.repository.AssessmentRepository;
import avd.informatica.studentregistration.repository.CourseRepository;
import avd.informatica.studentregistration.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
// todo see created tables using: http://localhost:8080/h2-ui/

// Annotating a class:
// - with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
// - The @Bean annotation tells Spring that a method annotated with @Bean will return an object that should be registered as a bean in the Spring application context.
@Configuration
public class LoadDummyData {
    private static final Logger log = LoggerFactory.getLogger(LoadDummyData.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository, CourseRepository courseRepository, AssessmentRepository assessmentRepository) {
        return args -> {
//            repository.save(new Student("naam", LocalDate.of(1998, 8, 5)));
            Student piet = new PartimeStudent("Piet", LocalDate.of(1999, 8, 19), "CM");
            Student jan = new FulltimeStudent("Jan", LocalDate.of(2001, 8, 19), "Joli");
            Student sandra = new FulltimeStudent("Sandra", LocalDate.of(2004, 8, 19), "Pieter");
            Student ivo = new PartimeStudent("Ivo", LocalDate.of(1967, 5, 8), "Bol");

            log.info("Preloading " + studentRepository.save(piet));
            log.info("Preloading " + studentRepository.save(jan));
            log.info("Preloading " + studentRepository.save(sandra));
            log.info("Preloading " + studentRepository.save(ivo));

            Course javaIntro = new Course("Introduction to Java", "all you need to know to create...", 64, LocalDate.of(2021, 9, 1), LocalDate.of(2021, 11, 6));
            Course android = new Course("Java and mobile", "Create a cool Android app...", 64, LocalDate.of(2021, 11, 8), LocalDate.of(2022, 1, 29));
            log.info("Preloading " + courseRepository.save(javaIntro));
            log.info("Preloading " + courseRepository.save(android));

            log.info("Preloading " + courseRepository.save(new Course("asp.net", "Create web application in C#...", 64, LocalDate.of(2022, 2, 1), LocalDate.of(2021, 11, 6))));
            log.info("Preloading " + courseRepository.save(new Course("Xamarin and Maui", "create a multi platform mobile app", 64, LocalDate.of(2022, 4, 1), LocalDate.of(2021, 11, 6))));

//            List<Student> maybePiet = studentRepository.findStudentByNameContainingIgnoringCase("Piet");
//            List<Course> maybeJava = courseRepository.findByTitleContainingIgnoringCase("Java");

            log.info("Preloading " + assessmentRepository.save(new Assessment(LocalDate.of(2021, 11, 4), piet, javaIntro)));
            log.info("Preloading " + assessmentRepository.save(new Assessment(LocalDate.of(2021, 11, 4), sandra, javaIntro)));
            log.info("Preloading " + assessmentRepository.save(new Assessment(LocalDate.of(2021, 11, 4), piet, android)));
        };
    }
}
