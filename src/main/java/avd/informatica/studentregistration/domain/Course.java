package avd.informatica.studentregistration.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Course {
    @lombok.Setter
    @lombok.Getter
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @lombok.Setter
    @lombok.Getter
    private String title;
    @lombok.Setter
    @lombok.Getter
    private String description;
    @lombok.Setter
    @lombok.Getter
    private int capacity;
    @lombok.Setter
    @lombok.Getter
    private LocalDate startDate;
    @lombok.Setter
    @lombok.Getter
    private LocalDate endDate;

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
