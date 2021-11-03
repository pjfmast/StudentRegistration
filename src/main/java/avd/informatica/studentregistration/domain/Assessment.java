package avd.informatica.studentregistration.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Assessment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @OneToOne
    private Student student;
    @OneToOne
    private Course course;

    public Assessment(LocalDate date, Student student, Course course) {
        this.date = date;
        this.student = student;
        this.course = course;
    }

    public Assessment() {
    }
}
