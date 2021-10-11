package avd.informatica.studentregistration.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
/*
we are using a new annotation @JsonIdentityInfo.
In the previous aricles, we have StackOverflow errors due to circular references.
We have been using @JsonIgnore, @JsonManagedReference, and @JsonBackReference to take care of the error.
This new annotation, @JsonIdentityInfo, will handle the circular reference errors for us.
 */
@lombok.Setter
@lombok.Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;

    // Add many-to-many relation between Course and Student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course(String title, String description, int capacity, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

}
