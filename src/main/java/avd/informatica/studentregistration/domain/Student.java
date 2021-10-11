package avd.informatica.studentregistration.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PartimeStudent.class, name = "PARTTIME"),
        @JsonSubTypes.Type(value = FulltimeStudent.class, name = "FULLTIME")
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@lombok.Setter
@lombok.Getter
public abstract class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
   private Long id;

    private String name;
    private LocalDate dayOfBirth;

    /*
        Here, Student will be the parent entity,
        and we are using mappedBy="students" in the role entity.
        The @ManytoMany annotation shows that it is a Many to Many relationship,
         and using @ManytoMany annotations at both sides of the relation
          (i.e.: in Student Entity and Course Entity) shows that it is a bidirectional relation.
     */
//    @ManyToMany(targetEntity = Course.class, mappedBy = "students", cascade = CascadeType.ALL)
    // We are not using cascade type ALL,
    // as this may propagate the delete operation to the courses as well
    // and deletes all the associated courses if a student object is deleted.
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = {@JoinColumn(name = "STUDENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COURSE_ID")}
    )
    @lombok.Setter
    @lombok.Getter
    private Set<Course> courses;

    public Student(String name, LocalDate dayOfBirth) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
    }

    public Student() {
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course) {
        courses.add(course);
        course.getStudents().remove(this);
    }
}
