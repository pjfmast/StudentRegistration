package avd.informatica.studentregistration.controller;

import avd.informatica.studentregistration.domain.Course;
import avd.informatica.studentregistration.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public CourseController( CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public ResponseEntity<List<Course>> all(@RequestParam(required = false) String title) {
        List<Course> found = new ArrayList<>();

        if (title == null) {
            found.addAll(courseRepository.findAll());
        } else {
            found.addAll(courseRepository.findByTitleContainingIgnoringCase(title));
        }
        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Course>> upComingCourses() {
        List<Course> found = new ArrayList<>(courseRepository.findByStartDateAfter(LocalDate.now()));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<Course> newCourse(@RequestBody Course newCourse) {

        try {
            Course _course = courseRepository.save(newCourse);
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Error during creation of: " + newCourse, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        if ((!courseRepository.existsById(id))) {
            // HTTP 404 Not Found response is for (deleting) a resource does not exist
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);
        // HTTP 204 No Content: The server successfully processed the request, but is not returning any content
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Course> updateCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(newCourse.getTitle());
            course.setCapacity(newCourse.getCapacity());
            course.setDescription(newCourse.getDescription());
            course.setStartDate(newCourse.getStartDate());
            course.setEndDate(newCourse.getEndDate());

            return ResponseEntity.ok(courseRepository.save(course));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
