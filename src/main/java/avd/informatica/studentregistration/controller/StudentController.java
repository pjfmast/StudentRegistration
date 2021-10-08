package avd.informatica.studentregistration.controller;

import avd.informatica.studentregistration.domain.Student;
import avd.informatica.studentregistration.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    private final StudentRepository studentRepository;


    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getAll(@RequestParam(required = false) String name) {
        List<Student> found = new ArrayList<>();

        if (name == null || name.isEmpty()) {
            found.addAll(studentRepository.findAll());
        } else {
            found.addAll(studentRepository.findStudentByNameContainingIgnoringCase(name));
        }
        return found;
    }

    @GetMapping("/immature")
    public ResponseEntity<List<Student>> getImmatureStudents() {
        LocalDate immatureDate = LocalDate.now().minusYears(18).plusDays(1);
        List<Student> found = new ArrayList<>(studentRepository.findStudentByDayOfBirthBetween(immatureDate, LocalDate.now()));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping("/multiple")
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
