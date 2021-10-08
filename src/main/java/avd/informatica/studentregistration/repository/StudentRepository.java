package avd.informatica.studentregistration.repository;

import avd.informatica.studentregistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByNameContainingIgnoringCase(String name);
    List<Student> findStudentByDayOfBirthBetween(LocalDate fromDate, LocalDate toDate);
}
